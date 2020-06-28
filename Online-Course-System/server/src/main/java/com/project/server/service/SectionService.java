package com.project.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.server.dao.SectionMapper;
import com.project.server.dto.PageDto;
import com.project.server.dto.SectionDto;
import com.project.server.entity.Section;
import com.project.server.entity.SectionExample;
import com.project.server.util.CopyUtil;
import com.project.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     * List query.
     */
    public void list(PageDto<SectionDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());  // The first select sentence after startPage() will be executed pagination.
        SectionExample sectionExample = new SectionExample();
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setList(sectionDtoList);
    }

    /**
     * Save a section. If there is an ID in sectionDto, we update it. Otherwise, we create it.
     */
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())) {
            this.insert(section);
        }
        else {
            this.update(section);
        }
    }

    /**
     * Insert a new section.
     */
    private void insert(Section section) {
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
     * Update a section.
     */
    private void update(Section section) {
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
     * Delete a section.
     */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}