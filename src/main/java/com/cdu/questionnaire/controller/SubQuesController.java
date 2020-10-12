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
        String content = jsonObject.getString("content");
        JSONObject jsonObject1 = new JSONObject(content);
        JSONArray content1 = jsonObject1.getJSONArray("content");
        for (int i = 0; i < content1.length(); i++) {
            Map<String, Object> map = new HashMap<>();
            JSONObject jsonObject2 = content1.getJSONObject(i);
            Integer id = (Integer) jsonObject2.get("id");
            String value = jsonObject2.getString("value");
            map.put("id", id);
            map.put("value", value);
            map.put("userId", userId);
            mapList.add(map);
        }
        if (quesId != null && userId != null) {
            //提交问卷状态
            Integer checkIsSub = subQuesService.checkIsSub(userId, quesId);
            //写入内容
            Integer writeConQues = subQuesService.writeConQues(mapList);
            if (checkIsSub == 0 || writeConQues == 0) {
                return "{\"status\" : \"0\"}";
            } else {
                return "{\"status\" : \"1\"}";
            }
        } else {
            return "{\"status\" : \"0\"}";
        }
    }

}
