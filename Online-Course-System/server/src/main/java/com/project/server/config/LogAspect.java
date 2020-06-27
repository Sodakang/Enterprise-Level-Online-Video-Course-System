package com.project.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;

import com.project.server.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /** Define an aspect. */
    @Pointcut("execution(public * com.project.*.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // Log ID.
        MDC.put("UUID", UuidUtil.getShortUuid());

        // Start printing out the request logs.
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // Start printing out the business operations.
        String nameCn = "";
        if (name.contains("list") || name.contains("query")) {
            nameCn = "Select";
        } else if (name.contains("save")) {
            nameCn = "Save";
        } else if (name.contains("delete")) {
            nameCn = "Delete";
        } else {
            nameCn = "Operate";
        }

        // Use reflection to achieve the business name.
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("No business name");
        } catch (SecurityException e) {
            LOG.error("Failed to get the business name", e);
        }

        // Print out the request information.
        LOG.info("------------- [{}]{} Begin -------------", businessName, nameCn);
        LOG.info("Request Address: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("Class and Method Name: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("Remote Address: {}", request.getRemoteAddr());

        // Print out the request parameters.
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // Exclude some fields, so that some sensitive fields and the fields that are too long will not be displayed.
        String[] excludeProperties = {"shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("The request parameters: {}", JSONObject.toJSONString(arguments, excludeFilter)); // It will not be printed if it is null.
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // Exclude the sensitive fields and the fields which are too long.
        String[] excludeProperties = {"password", "shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("Return results: {}", JSONObject.toJSONString(result, excludeFilter));
        LOG.info("------------- Finish: it takes：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
