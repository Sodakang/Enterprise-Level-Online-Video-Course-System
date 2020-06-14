package com.project.server.service;

import com.project.server.dao.TestMapper;
import com.project.server.entity.Test;
import com.project.server.entity.TestExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andIdEqualTo("1");
        // testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
