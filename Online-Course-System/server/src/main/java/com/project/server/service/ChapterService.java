package com.project.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.server.dao.ChapterMapper;
import com.project.server.dto.ChapterDto;
import com.project.server.dto.PageDto;
import com.project.server.entity.Chapter;
import com.project.server.entity.ChapterExample;
import com.project.server.util.CopyUtil;
import com.project.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void list(PageDto<ChapterDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());  // The first select sentence after startPage() will be executed pagination.
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
//        List<ChapterDto> chapterDtoList = new ArrayList<>();
//        for(Chapter chapter: chapterList) {
//            ChapterDto chapterDto = new ChapterDto();
//            BeanUtils.copyProperties(chapter, chapterDto);
//            chapterDtoList.add(chapterDto);
//        }
        List<ChapterDto> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDto chapterDto) {
        chapterDto.setId(UuidUtil.getShortUuid());
//        Chapter chapter = new Chapter();
//        BeanUtils.copyProperties(chapterDto, chapter);
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        chapterMapper.insert(chapter);
    }
}
