package com.project.server.dao;

import com.project.server.entity.Section;
import com.project.server.entity.SectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    long countByExample(SectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int deleteByExample(SectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int insert(Section record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int insertSelective(Section record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    List<Section> selectByExample(SectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    Section selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int updateByPrimaryKeySelective(Section record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table section
     *
     * @mbg.generated Sat Jun 27 16:43:45 PDT 2020
     */
    int updateByPrimaryKey(Section record);
}