package com.cdu.questionnaire.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Question
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/28 0:09
 * @Description 对应数据库问卷表
 * Modification User:
 * Modification Date:
 */
@Data
public class Question {
    private Integer id;
    private String quesName;
    private String quesInfo;
}
