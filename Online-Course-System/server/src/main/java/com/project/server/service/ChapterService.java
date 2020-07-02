package com.project.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.server.dao.ChapterMapper;
import com.project.server.dto.ChapterDto;
import com.project.server.dto.ChapterPageDto;
import com.project.server.dto.PageDto;
import com.project.server.entity.Chapter;
import com.project.server.entity.ChapterExample;
import com.project.server.util.CopyUtil;
import com.project.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * List query.
     */
    public void list(ChapterPageDto chapterPageDto) {
        PageHelper.startPage(chapterPageDto.getPage(), chapterPageDto.getSize());  // The first select sentence after startPage() will be executed pagination.
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        if(!StringUtils.isEmpty(chapterPageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        chapterPageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        chapterPageDto.setList(chapterDtoList);
    }

    /**
     * Save a chapter. If there is an ID in chapterDto, we update it. Otherwise, we create it.
     */
    public void save(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if(StringUtils.isEmpty(chapterDto.getId())) {
            this.insert(chapter);
        }
        else {
            this.update(chapter);
        }
    }

    /**
     * Insert a new chapter.
     */
    private void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }

    /**
     * Update a chapter.
     */
    private void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }

    /**
     * Delete a chapter.
     */
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
