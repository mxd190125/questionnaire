package com.cdu.questionnaire.pojo;

import lombok.Data;

/**
 * @ClassName Field
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/28 0:13
 * @Description 题目
 * Modification User:
 * Modification Date:
 */
@Data
public class Field {
    private Integer id;
    private String fieldName;
    private String chosse;
    private Integer quesId;
    private String chosseType;
}
