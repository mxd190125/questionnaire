package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping(value = "/test")
    public String test(){
        return testService.test();
    }

}
