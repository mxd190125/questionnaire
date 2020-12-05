package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.pojo.Question;
import com.cdu.questionnaire.pojo.UserSubmit;
import com.cdu.questionnaire.service.UserService;
import com.cdu.questionnaire.utils.UpLoadUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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

//    /**
//     * 获得所有未提交
//     * @param userName
//     * @param isSub
//     * @return
//     * @throws JsonProcessingException
//     */
//    @GetMapping(value = "/user/home/unSubQuestionNaires")
//    public String findUnSubQuestionNaires(String userName , int isSub) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String result="";
//        List<Question> questionList = new ArrayList<>();
//
//        log.info("###### username: "+userName+" isSub: "+isSub);
//        if (userName !=null && userName.length() !=0){
//            questionList=userService.findUnSubQuestionNaires(userName , isSub);
//            if (questionList!=null){
//                result="{\"status\":"+STATUS_SUCCESS+
//                        ",\"isSub\":"+isSub+
//                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                        "}";
//            }else {
//                result="{\"status\":"+STATUS_UN_SUCCESS+
//                        ",\"isSub\":"+isSub+
//                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                        "}";
//            }
//        }else {
//            result="{\"status\":"+STATUS_UN_SUCCESS+
//                    ",\"isSub\":"+isSub+
//                    ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                    "}";
//        }
//        return result;
//    }

//    /**
//     * 获取所有已提交的问卷
//     * @param userName
//     * @return
//     */
//    @GetMapping(value = "/user/home/subQuestionNaires")
//    public String findSubOrSubQuestionNaires(String userName , int isSub) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String result="";
//        List<UserSubmit> questionList = new ArrayList<>();
//
//        log.info("###### username: "+userName+" isSub: "+isSub);
//        if (userName !=null && userName.length() !=0){
//            questionList=userService.findSubQuestionNaires(userName , isSub);
//            if (questionList!=null){
//                result="{\"status\":"+STATUS_SUCCESS+
//                        ",\"isSub\":"+isSub+
//                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                        "}";
//            }else {
//                result="{\"status\":"+STATUS_UN_SUCCESS+
//                        ",\"isSub\":"+isSub+
//                        ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                        "}";
//            }
//        }else {
//            result="{\"status\":"+STATUS_UN_SUCCESS+
//                    ",\"isSub\":"+isSub+
//                    ",\"questionNaires\":"+mapper.writeValueAsString(questionList)+
//                    "}";
//        }
//        return result;
//    }



//    /**
//     * 展示某个已提交问卷内容
//     * @param userName
//     * @param quesId
//     * @return
//     */
//    @PostMapping(value = "/user/history/showSubQuestionNaire")
//    public String showSubQuestionNaire(String userName , int quesId) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String result="";
//        //问卷基本信息
//        Question question=new Question();
//        //问卷各题答案
//        List<Map<String, Object>> fieldValueList=new ArrayList<>();
//        //提交的时间
//        String subTime="";
//
//        if (userName !=null && userName.length() !=0){
//            question=userService.getQuestionNaire(quesId);
//            fieldValueList=userService.getFieldValues(userName, quesId);
//            subTime=userService.getSubmitTime(userName, quesId);
//            result="{\"status\":"+STATUS_SUCCESS+
//                    ",\"question\":"+mapper.writeValueAsString(question)+
//                    ",\"fieldValueList\":"+mapper.writeValueAsString(fieldValueList)+
//                    ",\"subTime\":"+mapper.writeValueAsString(subTime)+
//                    "}";
//        }else {
//            result="{\"status\":"+STATUS_UN_SUCCESS+
//                    ",\"question\":"+mapper.writeValueAsString(question)+
//                    ",\"fieldValueList\":"+mapper.writeValueAsString(fieldValueList)+
//                    ",\"subTime\":"+mapper.writeValueAsString(subTime)+
//                    "}";
//        }
//        return result;
//    }


    /**
     * 下面是第二阶段开发的代码
     */
    @GetMapping(value = "/user/home/getDoctorIdAndUserId")
    public String getDoctorIdAndUserId(String userName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        HashMap<String, Object> map = null;
        if (userName !=null && userName.length() !=0){
            map = userService.getDoctorIdAndUserIdByUserName(userName);
            result="{\"status\":"+STATUS_SUCCESS +
                    ",\"doctorIdAndUserId\":" + mapper.writeValueAsString(map) +
                    "}";
        }else {
            result="{\"status\":"+STATUS_UN_SUCCESS +
                    ",\"doctorIdAndUserId\":" + mapper.writeValueAsString(map) +
                    "}";
        }
        return result;
    }

    /**
     * 获取某个已提交问卷的内容
     * @return
     */
    @GetMapping(value = "/user/home/showSubQuestionNaire")
    public String showSubQuestionNaire(int subId ,  int quesId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result="";

        if (quesId == 1){
            Question question=userService.getQuestionNaire(quesId);
            List<HashMap<String, Object>> content=userService.getSubAnswerFieldsAndValue(quesId , subId);
            result="{\"status\":"+STATUS_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"content\":"+mapper.writeValueAsString(content)+
                    "}";
        }else if (quesId == 2){
            Question question=userService.getQuestionNaire(quesId);
            HashMap<String, Object> content = userService.getPsaValue(subId);
            result="{\"status\":"+STATUS_SUCCESS+
                    ",\"question\":"+mapper.writeValueAsString(question)+
                    ",\"content\":"+mapper.writeValueAsString(content)+
                    "}";
        }

        return result;
    }

    @GetMapping(value = "/user/home/findTowUnSubQuestionNaire")
    public String findTowUnSubQuestionNaire(int userId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        HashMap<String, Object> answerInfo = userService.getUnSubAnswerInfo(userId);
        HashMap<String, Object> psa = userService.getUnSubPSAInfo(userId);
        if (answerInfo != null){
            List<Map<String, Object>> field = userService.getUnSubAnswerFields((Integer) answerInfo.get("quesId"));
            answerInfo.put("fields" , field);
            answerInfo.put("pubTime" , answerInfo.get("pubTime"));
            answerInfo.put("endTime" , answerInfo.get("endTime"));
            result="{\"status\":"+STATUS_SUCCESS +
                    ",\"answer\":" + mapper.writeValueAsString(answerInfo) +
                    ",\"psa\":" + mapper.writeValueAsString(psa) +
                    "}";
        }else {
            result="{\"status\":"+STATUS_SUCCESS +
                    ",\"answer\":" + mapper.writeValueAsString(answerInfo) +
                    ",\"psa\":" + mapper.writeValueAsString(psa) +
                    "}";
        }
        return result;
    }

    /**
     * 获取用户某个时间提交的问卷下的所有回复通知
     * @param userId
     * @param senderId
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/user/home/notice")
    public String getMessageByUser(int userId , int senderId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        List<HashMap<String, Object>> messages = userService.getMessageByUser(userId , senderId);
        result="{\"status\":"+STATUS_SUCCESS +
                ",\"messages\":" + mapper.writeValueAsString(messages) +
                "}";
        return result;
    }


    /**
     * 提交PSA问卷
     * @param photo
     * @param selectTime
     * @param subId
     * @return
     * @throws FileNotFoundException
     */
    @PostMapping(value = "/user/home/subPSA")
    public String subPsaQuestionnaire(@RequestParam(value = "photo" , required = false) MultipartFile photo , String selectTime , int subId) throws FileNotFoundException {
        String result = "";
        if (!photo.isEmpty()){
            String photoUrl = UpLoadUtils.uploadPhoto(photo);
            boolean b = userService.insertIntoPSA(photoUrl, selectTime, subId);
            result = b ? "{\"status\":"+STATUS_SUCCESS +"}" : "{\"status\":"+STATUS_UN_SUCCESS +"}";
        }else {
            result = "{\"status\":"+STATUS_UN_SUCCESS +"}";
        }
        return result;
    }

    @PostMapping(value = "/user/home/subAnswer" , produces = "application/json;charset=UTF-8")
    public String subAnswerQuestionnire(@RequestBody String json) throws JSONException {
        String result = "";

        JSONObject jsonObject = new JSONObject(json);
        int subId = jsonObject.getInt("subId");
        int subScore = jsonObject.getInt("subScore");
        JSONArray subContent = jsonObject.getJSONArray("subContent");

        List<HashMap<String , Object>> fieldList = new ArrayList<>();
        List<HashMap<String , Object>> unFieldList = new ArrayList<>();
        for (int i = 0; i < subContent.length(); i++) {
            JSONObject item = subContent.getJSONObject(i);
            /***
             * 分离普通的题型和复合题型
             */
            if (item.getInt("type") == 6){
                JSONArray array = item.getJSONArray("chosseValue");
                for (int j = 0; j < array.length(); j++) {
                    HashMap<String, Object> map = new HashMap<>();
                    JSONObject item2 = array.getJSONObject(j);
                    map.put("unFieldId" , item2.getInt("unFieldId"));
                    map.put("chosseValue" , item2.getString("chosseValue"));
                    map.put("subId" , subId);
                    unFieldList.add(map);
                }
            }else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("fieldId" , item.getInt("fieldId"));
                map.put("chosseValue" , item.getString("chosseValue"));
                map.put("subId" , subId);
                fieldList.add(map);
            }
        }

        boolean b = userService.insertIntoAnswer(fieldList, unFieldList ,subScore ,subId);
        result = b ? "{\"status\":"+STATUS_SUCCESS +"}" : "{\"status\":"+STATUS_UN_SUCCESS +"}";

        return result;
    }

    @GetMapping(value = "/user/history/showAllSubQuestion")
    public String historicalQuestionNaires(int userId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        List<HashMap<String, Object>> list = userService.findAllSubQuestionnaires(userId);
        result = "{\"status\":"+STATUS_SUCCESS +
                ",\"list\":" + mapper.writeValueAsString(list) +
                "}";
        return result;
    }
}