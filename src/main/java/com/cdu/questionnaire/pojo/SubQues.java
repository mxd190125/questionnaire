package com.cdu.questionnaire.pojo;

import lombok.Data;

@Data
public class SubQues {
    private String quesId;
    private String userName;
    private Content[] content;
}
