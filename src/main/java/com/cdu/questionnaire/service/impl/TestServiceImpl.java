package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.TestDao;
import com.cdu.questionnaire.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public String test() {
        return testDao.test();
    }
}
