package com.cdu.questionnaire.service;

import com.cdu.questionnaire.pojo.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getMessages(int senderId , int subId);

    int writeChatLog(Message message);

    int getSubScore(int subId);
}
