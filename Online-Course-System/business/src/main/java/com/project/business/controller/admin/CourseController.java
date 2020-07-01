package com.project.business.controller.admin;

import com.project.server.dto.CourseDto;
import com.project.server.dto.PageDto;
import com.project.server.dto.ResponseDto;
import com.project.server.service.CourseService;
import com.project.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

    public static final String BUSINESS_NAME = "Course";

    @Resource
    private CourseService courseService;

    /**
     * List query.
     */
    @PostMapping("/list")
    public ResponseDto<PageDto<CourseDto>> list(@RequestBody PageDto<CourseDto> pageDto) {
//        LOG.info("pageDto: {}", pageDto);
        ResponseDto<PageDto<CourseDto>> responseDto = new ResponseDto<>();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * Save a course. If there is an ID in courseDto, we update it. Otherwise, we create it.
     */
    @PostMapping("/save")
    public ResponseDto<CourseDto> save(@RequestBody CourseDto courseDto) {
//        LOG.info("courseDtp: {}", courseDto);
        // Check before saving.
        ValidatorUtil.require(courseDto.getName(), "Name");
        ValidatorUtil.length(courseDto.getName(), "Name", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "Summary", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "Cover Image", 1, 100);
        ResponseDto<CourseDto> responseDto = new ResponseDto<>();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * Delete a course.
     */
    @RequestMapping("/delete/{id}")
    public ResponseDto<CourseDto> delete(@PathVariable String id) {
//        LOG.info("id: {}", id);
        ResponseDto<CourseDto> responseDto = new ResponseDto<>();
        courseService.delete(id);
        return responseDto;
    }

}