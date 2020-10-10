package com.cdu.questionnaire.controller;


import com.cdu.questionnaire.service.SubQuesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SubQuesController
 * @Version 1.0
 * @Author 何政梁
 * @Date 2020/9/28 15:21
 * @Description TODO
 * Modification User:
 * Modification Date:
 */

@RestController
public class SubQuesController {

    @Resource
    private SubQuesService subQuesService;



    @PostMapping(value = "/user/home/subQues",produces = "application/json;charset=UTF-8")
    public String subQues(@RequestBody Map<String,Object> params) throws JSONException {
        if (params == null){
            System.out.println("系统接收参数错误");
            return "{\"status\" : \"0\"}";
        }

        // 问卷id
        String quesId = params.get("quesId").toString();
        // 用户id
        String userName = params.get("userName").toString();
        String userId = subQuesService.queryId(userName);

        List<Map<String,Object>> mapList = new ArrayList<>();
        Object content = params.get("content");
        String s = String.valueOf(content);
        JSONArray jsonArray = new JSONArray(s);
        for (int i=0;i<jsonArray.length();i++){
            Map<String,Object> map = new HashMap<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put("id",jsonObject.get("id"));
            map.put("value",jsonObject.get("value"));
            map.put("userId",userId);
            mapList.add(map);
        }
        if (quesId !=null && userId != null){
            // 记录提交时间

            //提交问卷状态
            Integer checkIsSub = subQuesService.checkIsSub(userId, quesId);
            //写入内容
            Integer writeConQues = subQuesService.writeConQues(mapList);
            if (checkIsSub == 0 || writeConQues == 0){
                return "{\"status\" : \"0\"}";
            }else {
                return "{\"status\" : \"1\"}";
            }
        }else {
            return "{\"status\" : \"0\"}";
        }
    }





























}
