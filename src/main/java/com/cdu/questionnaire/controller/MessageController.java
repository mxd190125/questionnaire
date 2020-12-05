package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.pojo.Message;
import com.cdu.questionnaire.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MessageController {
    private static final String STATUS_SUCCESS="1";
    private static final String STATUS_UN_SUCCESS="0";

    @Resource
    private MessageService messageService;

    /**
     * 获取基于某个时间提交的问卷下的所有留言内容
     * @param subId
     * @param senderId
     * @return
     * @throws JsonProcessingException
     */
//    @GetMapping(value = "/user/home/messages")
    @GetMapping(value = "/message/messages")
    public String getMessage(int subId , int senderId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        List<Message> messages = messageService.getMessages(senderId , subId);
        int subScore = messageService.getSubScore(subId);
        result="{" +
                "\"status\":"+STATUS_SUCCESS +
                ",\"subScore\":"+subScore +
                ",\"messages\":" + mapper.writeValueAsString(messages) +
                "}";
        return result;
    }

    /**
     * 向对方发送消息
     * @param message 消息类
     * @return 成功是否成功及  消息集合（刷新）
     */
    @PostMapping("/message/sendMessage")
    public Map<String,Object> sendMessage(Message message){
        HashMap<String, Object> map = new HashMap<>();
        int isSuccess = messageService.writeChatLog(message);
        map.put("status",isSuccess);
//        List<ChatLog> chatLogList = messageService.queryAllChatLog(chatLog.getSubId(),chatLog.getSenderId());
//        map.put("chatLogList",chatLogList);
        return map;
    }
}
