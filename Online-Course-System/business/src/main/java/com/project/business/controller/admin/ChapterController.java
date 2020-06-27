package com.project.business.controller.admin;

import com.project.server.dto.ChapterDto;
import com.project.server.dto.PageDto;
import com.project.server.dto.ResponseDto;
import com.project.server.entity.Chapter;
import com.project.server.exception.ValidatorException;
import com.project.server.service.ChapterService;
import com.project.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto<PageDto<ChapterDto>> list(@RequestBody PageDto<ChapterDto> pageDto) {
        LOG.info("pageDto: {}", pageDto);
        ResponseDto<PageDto<ChapterDto>> responseDto = new ResponseDto<>();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto<ChapterDto> save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDtp: {}", chapterDto);
        // Check before saving.
        ValidatorUtil.require(chapterDto.getName(), "Chapter name");
        ValidatorUtil.require(chapterDto.getCourseId(), "Course ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "Course ID", 1, 8);

        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @RequestMapping("/delete/{id}")
    public ResponseDto<ChapterDto> save(@PathVariable String id) {
        LOG.info("id: {}", id);
        ResponseDto<ChapterDto> responseDto = new ResponseDto<>();
        chapterService.delete(id);
        return responseDto;
    }

}
