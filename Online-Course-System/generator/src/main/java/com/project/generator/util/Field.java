package com.project.generator.util;

// Fields in MySQL database.
public class Field {

    // Comment: Example
    private String name; // Field name：course_id
    private String nameHump; // Field name with lowercase hump：courseId
    private String nameBigHump; // Field name with uppercase hump：CourseId
    private String type; // Field type：char(8)
    private String javaType; // Java type：String
    private String comment; // Use comment as the column name shown on the front-end.
    private Boolean nullAble;  // Can be null or not
    private Integer length;  // The length of the value in this field.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameHump() {
        return nameHump;
    }

    public void setNameHump(String nameHump) {
        this.nameHump = nameHump;
    }

    public String getNameBigHump() {
        return nameBigHump;
    }

    public void setNameBigHump(String nameBigHump) {
        this.nameBigHump = nameBigHump;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Field{");
        sb.append("name='").append(name).append('\'');
        sb.append(", nameHump='").append(nameHump).append('\'');
        sb.append(", nameBigHump='").append(nameBigHump).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", javaType='").append(javaType).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", nullAble=").append(nullAble);
        sb.append(", length=").append(length);
        sb.append('}');
        return sb.toString();
    }
}
