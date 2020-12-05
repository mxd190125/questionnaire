package com.cdu.questionnaire.pojo;

import lombok.Data;


@Data
public class Message {
    private Integer id;
    private Integer userId;
    private Integer doctorId;
    private Integer subId;
    private String content;
    private Integer senderId;
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String sendTime;
    private Integer isRead;
    private Integer score;
}
