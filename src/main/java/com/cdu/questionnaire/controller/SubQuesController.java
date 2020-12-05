package com.cdu.questionnaire.controller;


import com.cdu.questionnaire.service.SubQuesService;
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
 * @Description 用户提交问卷存储答案
 * Modification User:
 * Modification Date:
 */

@RestController
public class SubQuesController {

    @Resource
    private SubQuesService subQuesService;

    @PostMapping(value = "/user/home/subQues", produces = "application/json;charset=UTF-8")
    public String subQues(@RequestBody String params) throws JSONException {
        if (params == null) {
            System.out.println("系统接收参数错误");
            return "{\"status\" : \"0\"}";
        }
        //保存问卷题目答案以及用户id的集合
        List<Map<String, Object>> mapList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(params);

        //问卷id
        String quesId = jsonObject.getString("quesId");
        //获取用户名，然后在数据库获取该用户的id
        String userName = jsonObject.getString("userName");
        String userId = subQuesService.queryId(userName);
        //解析json数组
        JSONArray content = jsonObject.getJSONArray("content");
        for (int i = 0; i < content.length(); i++) {
            Map<String, Object> map = new HashMap<>();
            JSONObject jsonObject2 = content.getJSONObject(i);
            String id =  jsonObject2.getString("id");
            String value = jsonObject2.getString("value");
            map.put("id", id);
            map.put("value", value);
            map.put("userId", userId);
            mapList.add(map);
        }

        //保存问卷题目答案以及用户id的集合
        List<Map<String, Object>> mapUnList = new ArrayList<>();
        //复合选项题提交方法
        JSONArray unfed = jsonObject.getJSONArray("reunite");
        for (int i=0;i<unfed.length();i++){
            Map<String, Object> unMap = new HashMap<>();
            JSONObject jsonObject4 = unfed.getJSONObject(i);
            unMap.put("userId",userId);
            unMap.put("unFieldId",jsonObject4.getString("unFieldId"));
            unMap.put("unFieldValue",jsonObject4.getString("value"));
            mapUnList.add(unMap);
        }
        System.out.println(mapList);
        System.out.println(mapUnList);
        if (quesId != null && userId != null) {
            //提交问卷状态
            Integer checkIsSub = subQuesService.checkIsSub(userId, quesId);
            //写入内容
            Integer writeConQues = subQuesService.writeConQues(mapList);
            Integer writeConQuesUn = subQuesService.writeConQuesUn(mapUnList);
            if (checkIsSub == 0 || writeConQues == 0 || writeConQuesUn == 0) {
                return "{\"status\" : \"0\"}";
            } else {
                return "{\"status\" : \"1\"}";
            }
        } else {
            return "{\"status\" : \"0\"}";
        }

    }

}
