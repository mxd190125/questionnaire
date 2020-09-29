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

        //问卷内容
        List<Map<String,Object>> mapList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(params);
        JSONArray jsonArray = jsonObject.getJSONArray("content");
        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject object = jsonArray.getJSONObject(j);
            Map<String,Object> map = new HashMap<>(0);
            map.put("id",object.getString("id"));
            map.put("value",object.getString("value"));
            map.put("userId",userId);
            mapList.add(map);
        }
        if (quesId !=null && userId != null){
            // 记录提交时间
            //提交时间状态
            Integer recordSub = subQuesService.recordSub(userId, quesId);
            //提交问卷状态
            Integer checkIsSub = subQuesService.checkIsSub(userId, quesId);
            //写入内容
            Integer writeConQues = subQuesService.writeConQues(mapList);
            if (recordSub == 0 || checkIsSub == 0 || writeConQues == 0){
                return "{\"status\" : \"0\"}";
            }else {
                return "{\"status\" : \"1\"}";
            }
        }else {
            return "{\"status\" : \"0\"}";
        }
    }





























}
