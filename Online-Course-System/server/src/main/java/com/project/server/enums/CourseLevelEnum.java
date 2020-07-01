package com.project.server.enums;

public enum CourseLevelEnum {
    Beginner("1", "Beginner"),
    Intermediate("2", "Intermediate"),
    Advanced("3", "Advanced");

    private String code;

    private String desc;

    CourseLevelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
