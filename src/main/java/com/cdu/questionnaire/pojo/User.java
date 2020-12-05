package com.cdu.questionnaire.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Message 添加了doctorId
 * @ClassName 马雪冬
 * @Version 1.0
 * @Author dell
 * @Date 2020/9/27 17:56
 * @Description TODO
 * Modification User:
 * Modification Date:
 */
@Data
public class User {
    private String id;
    private String userName;
    private String role;
    private Integer isRegister;
    private String registerTime;
    private Integer doctorId;
}
