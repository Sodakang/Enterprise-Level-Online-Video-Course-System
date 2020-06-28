package com.project.${module}.controller.admin;

import com.project.server.dto.${Domain}Dto;
import com.project.server.dto.PageDto;
import com.project.server.dto.ResponseDto;
import com.project.server.service.${Domain}Service;
import com.project.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);

    public static final String BUSINESS_NAME = "${Domain}";

    @Resource
    private ${Domain}Service ${domain}Service;

    /**
     * List query.
     */
    @PostMapping("/list")
    public ResponseDto<PageDto<${Domain}Dto>> list(@RequestBody PageDto<${Domain}Dto> pageDto) {
//        LOG.info("pageDto: {}", pageDto);
        ResponseDto<PageDto<${Domain}Dto>> responseDto = new ResponseDto<>();
        ${domain}Service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * Save a ${domain}. If there is an ID in ${domain}Dto, we update it. Otherwise, we create it.
     */
    @PostMapping("/save")
    public ResponseDto<${Domain}Dto> save(@RequestBody ${Domain}Dto ${domain}Dto) {
//        LOG.info("${domain}Dtp: {}", ${domain}Dto);
        // Check before saving.


        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    /**
     * Delete a ${domain}.
     */
    @RequestMapping("/delete/{id}")
    public ResponseDto<${Domain}Dto> delete(@PathVariable String id) {
//        LOG.info("id: {}", id);
        ResponseDto<${Domain}Dto> responseDto = new ResponseDto<>();
        ${domain}Service.delete(id);
        return responseDto;
    }

}