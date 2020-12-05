package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.MessageDao;
import com.cdu.questionnaire.pojo.Message;
import com.cdu.questionnaire.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    @Transactional(rollbackFor = {RuntimeException.class , Exception.class})
    @Override
    public List<Message> getMessages(int senderId , int subId) {
        int i = messageDao.clearNoReadMessage(senderId, subId);
        return messageDao.getMessages(subId);
    }

    /**
     * 写入新消息
     *
     * @param message
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int writeChatLog(Message message) {
        return messageDao.insertChatLog(message);
    }

    @Override
    public int getSubScore(int subId) {
        return messageDao.getSubScore(subId);
    }


}
