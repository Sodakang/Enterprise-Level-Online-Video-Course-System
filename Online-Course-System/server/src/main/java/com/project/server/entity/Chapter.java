package com.project.server.entity;

public class Chapter {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chapter.id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chapter.course_id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chapter.name
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chapter.id
     *
     * @return the value of chapter.id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chapter.id
     *
     * @param id the value for chapter.id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chapter.course_id
     *
     * @return the value of chapter.course_id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chapter.course_id
     *
     * @param courseId the value for chapter.course_id
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chapter.name
     *
     * @return the value of chapter.name
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chapter.name
     *
     * @param name the value for chapter.name
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Jun 21 12:40:35 PDT 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}