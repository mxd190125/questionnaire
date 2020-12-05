package com.cdu.questionnaire.dao;

import com.cdu.questionnaire.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {

    public int clearNoReadMessage(@Param("senderId") int senderId , @Param("subId") int subId);

    public List<Message> getMessages(@Param("subId") int subId);

    int insertChatLog(Message message);

    int getSubScore(@Param("subId") int subId);

}
