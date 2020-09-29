package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.pojo.Question;
import com.cdu.questionnaire.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/27 20:09
 * @Description 用户权限下----相关操作
 * Modification User:
 * Modification Date:
 */
@Slf4j
@RestController
public class UserController {

    private static final String STATUS_SUCCESS="1";
    private static final String STATUS_UN_SUCCESS="0";
    private static final int SUBMIT_OK=1;
    private static final int SUBMIT_UN=0;

    @Resource
    private UserService userService;

    /**
     * 获取所有未或已提交的问卷
     * @param userName
     * @return
     */
    @GetMapping(value = "/user/home/questionNaires")
    public String findUnSubOrSubQuestionNaire(String userName , int isSub) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result="";
        List<Question> questionList = new ArrayList<>();

        log.info("###### username: "+userName+" isSub: "+isSub);
        if (userName !=null && userName.length() !=0){
            questionList=userService.findQuestionNaires(userName , isSub);
            if (questionList!=null){
                result="{\"status\":"+STATUS_SUCCESS+
                        ",\"isSub\":"+isSub+
                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
                        "}";
            }else {
                result="{\"status\":"+STATUS_UN_SUCCESS+
                        ",\"isSub\":"+isSub+
                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
                        "}";
            }
        }else {
            result="{\"status\":"+STATUS_UN_SUCCESS+
                    ",\"isSub\":"+isSub+
                    ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
                    "}";
        }
        return result;
    }

    /**
     * 获取某个未提交问卷的内容
     * @param userName
     * @return
     */
    @PostMapping(value = "/user/home/questionNaireContent")
    public String getQuestionNaireContent(String userName , int quesId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result="";

        Question question=new Question();
        List<Map<String, Object>> fieldList= new ArrayList<>();

        if (userName !=null && userName.length() !=0){
            question=userService.getQuestionNaire(quesId);
            fieldList=userService.getQuestionNaireFields(quesId);

            result="{\"status\":"+STATUS_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"fieldList\":"+mapper.writeValueAsString(fieldList)+
                    "}";
        }else {
            result="{\"status\":"+STATUS_UN_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"fieldList\":"+mapper.writeValueAsString(fieldList)+
                    "}";
        }

        return result;
    }

    /**
     * 展示某个已提交问卷内容
     * @param userName
     * @param quesId
     * @return
     */
    @PostMapping(value = "/user/history/showSubQuestionNaire")
    public String showSubQuestionNaire(String userName , int quesId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result="";
        //问卷基本信息
        Question question=new Question();
        //问卷题目集合
        List<Map<String, Object>> fieldList= new ArrayList<>();
        //问卷各题答案
        List<Map<String, Object>> fieldValueList=new ArrayList<>();
        //提交的时间
        String subTime="";

        if (userName !=null && userName.length() !=0){
            question=userService.getQuestionNaire(quesId);
            fieldList = userService.getQuestionNaireFields(quesId);
            fieldValueList=userService.getFieldValues(userName, quesId);
            subTime=userService.getSubmitTime(userName, quesId);
            result="{\"status\":"+STATUS_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"fieldList\":"+mapper.writeValueAsString(fieldList)+
                    ",\"fieldValueList\":"+mapper.writeValueAsString(fieldValueList)+
                    ",\"subTime\":"+mapper.writeValueAsString(subTime)+
                    "}";
        }else {
            result="{\"status\":"+STATUS_UN_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"fieldList\":"+mapper.writeValueAsString(fieldList)+
                    ",\"fieldValueList\":"+mapper.writeValueAsString(fieldValueList)+
                    ",\"subTime\":"+mapper.writeValueAsString(subTime)+
                    "}";
        }
        return result;
    }
}
