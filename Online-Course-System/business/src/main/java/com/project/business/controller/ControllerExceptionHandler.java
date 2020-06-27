package com.project.business.controller;

import com.project.server.dto.ChapterDto;
import com.project.server.dto.ResponseDto;
import com.project.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto<ChapterDto> validationExceptionHandler(ValidatorException e) {
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());
        responseDto.setMessage("Request Parameter Error!");
        return responseDto;
    }
}
