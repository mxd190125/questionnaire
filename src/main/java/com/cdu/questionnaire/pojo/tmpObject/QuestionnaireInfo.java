package com.cdu.questionnaire.pojo.tmpObject;

import lombok.Data;

/**
 * @Author 汪辉
 * @Date 2020/12/4 18:28
 * @Description : 医生端看提交和留言时所返回的对象
 */
@Data
public class QuestionnaireInfo {
    private Integer subId;//问卷id
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String subTime;
    private Integer quesId;
    private String name;
    private Integer isRead;
}
