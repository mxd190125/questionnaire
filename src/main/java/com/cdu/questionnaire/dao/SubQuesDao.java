package com.cdu.questionnaire.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubQuesDao {

    /**
     * 返回用户id
     * @param userName 用户名
     * @return 用户在数据库的Id
     */
    String queryId(@Param("userName") String userName);



    /**
     * 记录用户提交状态
     * @param userId 用户数据库id
     * @param quesId 问卷Id
     * @return 状态码
     */
    Integer checkIsSub(@Param("userId") String userId, @Param("quesId") String quesId);

    /**
     * 传入用户填写问卷内容
     * @param mapList 内容的数据封装
     * @return 状态码
     */
    Integer writeConQues(@Param("list") List<Map<String, Object>> mapList);

    /**
     * 传入用户填写问卷内容
     * @param mapList 内容的数据封装
     * @return 状态码
     */
    Integer writeConQuesUn(@Param("list") List<Map<String, Object>> mapList);
}
