package com.cdu.questionnaire.pojo;

import lombok.Data;

@Data
public class UserSubmit {
    private Integer id;
    private String quesName;
    private String quesInfo;
    private Integer userId;
    private Integer quesId;
    private Integer isSub;
    private String subTime;
}
