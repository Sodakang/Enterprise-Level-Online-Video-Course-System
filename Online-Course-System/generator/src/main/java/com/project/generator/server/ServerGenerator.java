package com.project.generator.server;

import com.project.generator.util.DbUtil;
import com.project.generator.util.Field;
import com.project.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

public class ServerGenerator {

    private static String MODULE = "business";

    private static String toServicePath = "server//src//main//java//com//project//server//service//";

    private static String toControllerPath = MODULE + "//src//main//java//com//project//" +
            MODULE + "//controller//admin//";

    private static String toDtoPath = "server//src//main//java//com//project//server//dto//";

    /**
     * Get all the Java types and use set to remove the duplicates.
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String module = MODULE;
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // Generate dto.
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);

//        // Generate service.
//        FreemarkerUtil.initConfig("service.ftl");
//        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);
//
//        // Generate controller.
//        FreemarkerUtil.initConfig("controller.ftl");
//        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);
    }
}
