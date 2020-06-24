package com.project.server.dto;

public class ResponseDto<T> {

    // Success or fail in business.
    private boolean success = true;

    // The return code.
    private String code;

    // The return message.
    private String message;

    // Return generic type data.
    private T content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
