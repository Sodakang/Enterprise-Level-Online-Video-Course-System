package com.project.server.service;

import com.github.pagehelper.PageHelper;
import com.project.server.dao.ChapterMapper;
import com.project.server.dto.ChapterDto;
import com.project.server.entity.Chapter;
import com.project.server.entity.ChapterExample;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        PageHelper.startPage(1, 1);  // The first select sentence after startPage() will be executed pagination.
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for(Chapter chapter: chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
