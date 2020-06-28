package com.project.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.server.dao.${Domain}Mapper;
import com.project.server.dto.${Domain}Dto;
import com.project.server.dto.PageDto;
import com.project.server.entity.${Domain};
import com.project.server.entity.${Domain}Example;
import com.project.server.util.CopyUtil;
import com.project.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * List query.
     */
    public void list(PageDto<${Domain}Dto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());  // The first select sentence after startPage() will be executed pagination.
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
        pageDto.setList(${domain}DtoList);
    }

    /**
     * Save a ${domain}. If there is an ID in ${domain}Dto, we update it. Otherwise, we create it.
     */
    public void save(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if(StringUtils.isEmpty(${domain}Dto.getId())) {
            this.insert(${domain});
        }
        else {
            this.update(${domain});
        }
    }

    /**
     * Insert a new ${domain}.
     */
    private void insert(${Domain} ${domain}) {
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }

    /**
     * Update a ${domain}.
     */
    private void update(${Domain} ${domain}) {
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    /**
     * Delete a ${domain}.
     */
    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}