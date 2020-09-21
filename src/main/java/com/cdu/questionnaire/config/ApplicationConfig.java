package com.cdu.questionnaire.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.cdu.questionnaire.dao"})
public class ApplicationConfig {

}
