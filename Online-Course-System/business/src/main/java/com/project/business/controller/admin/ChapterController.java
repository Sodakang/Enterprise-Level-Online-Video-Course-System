package com.project.business.controller.admin;

import com.project.server.dto.ChapterDto;
import com.project.server.dto.PageDto;
import com.project.server.entity.Chapter;
import com.project.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public PageDto<ChapterDto> list(@RequestBody PageDto<ChapterDto> pageDto) {
        LOG.info("pageDto: {}", pageDto);
        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDtp: {}", chapterDto);
        chapterService.save(chapterDto);
        return chapterDto;
    }

}
