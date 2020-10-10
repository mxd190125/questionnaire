package com.cdu.questionnaire.pojo;

import lombok.Data;

/**
 * @ClassName FieldValue
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/28 0:15
 * @Description 题目的答案
 * Modification User:
 * Modification Date:
 */
@Data
public class FieldValue {
    private Integer id;
    private String fieldName;
    private String chosse;
    private Integer quesId;
    private String chosseType;
    private String chosseValue;
}
