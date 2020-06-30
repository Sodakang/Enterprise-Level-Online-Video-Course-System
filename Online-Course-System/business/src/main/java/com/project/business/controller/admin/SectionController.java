package com.project.business.controller.admin;

import com.project.server.dto.SectionDto;
import com.project.server.dto.PageDto;
import com.project.server.dto.ResponseDto;
import com.project.server.service.SectionService;
import com.project.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);

    public static final String BUSINESS_NAME = "Section";

    @Resource
    private SectionService sectionService;

    /**
     * List query.
     */
    @PostMapping("/list")
    public ResponseDto<PageDto<SectionDto>> list(@RequestBody PageDto<SectionDto> pageDto) {
//        LOG.info("pageDto: {}", pageDto);
        ResponseDto<PageDto<SectionDto>> responseDto = new ResponseDto<>();
        sectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * Save a section. If there is an ID in sectionDto, we update it. Otherwise, we create it.
     */
    @PostMapping("/save")
    public ResponseDto<SectionDto> save(@RequestBody SectionDto sectionDto) {
//        LOG.info("sectionDtp: {}", sectionDto);
        // Check before saving.
        ValidatorUtil.require(sectionDto.getTitle(), "Title");
        ValidatorUtil.length(sectionDto.getTitle(), "Title", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "Video Address", 1, 200);
        ResponseDto<SectionDto> responseDto = new ResponseDto<>();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    /**
     * Delete a section.
     */
    @RequestMapping("/delete/{id}")
    public ResponseDto<SectionDto> delete(@PathVariable String id) {
//        LOG.info("id: {}", id);
        ResponseDto<SectionDto> responseDto = new ResponseDto<>();
        sectionService.delete(id);
        return responseDto;
    }

}