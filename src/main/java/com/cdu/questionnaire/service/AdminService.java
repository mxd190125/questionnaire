package com.cdu.questionnaire.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Rose
 * @Date 2020.9.28
 */
public interface AdminService {

    /**
     *  查询待审核的用户
     * @return List<String>，一个包含所有待审核用户的List封装
     */
    List<String> queryUser();

    /**
     *  查询待审核的用户
     * @param pageNumber 点击的页数
     * @return List<String>，一个包含所有待审核用户的List封装
     */
    List<String> queryAllUser(Integer pageNumber);

    /**
     * 查询审核用户是否存在
     * @param userName 用户id
     * @return 返回状态码
     */
    int queryAuditUser(String userName);

    /**
     *   通过用户注册
     * @param userName 用户id
     * @return 返回是否审核是否成功的状态码
     */
    Integer agreeAuditUser(String userName);

    /**
     * 返回模糊查询的所有用户
     * @param dynamicId 用户模糊id
     * @return 返回用户集合
     */
    List<String> searchUser(String dynamicId);

    /**
     *  查询未过期问卷
     *  @param userName 用户id
     * @return List<Map<String,String>>，一个包含所有未过期问卷的List封装
     */
    List<Map<String,Object>> queryQuesTime(String userName);

    /**
     *  有效问卷
     * @param list 数据封装
     * @return List<String>，有效问卷的List封装
     */
    Integer writeQuesById(List<Map<String, Object>> list);

    /**
     * 查询改用户提交的所有问卷，按照提交时间逆序
     * @param pageNumber 点击的页数
     * @param userName 用户id
     * @return List<String>，一个包含该用户的提交问卷的List封装
     */
    List<String> queryQuesByUser(Integer pageNumber, String userName);

    /**
     *   拒绝用户注册
     * @param userName 用户id
     * @return 返回是否审核是否成功的状态码
     */
    Integer refuseAuditUser(@Param("userName") String userName);

    /**
     *  返回未填写的问卷内容
     * @param quesId 问卷id
     * @return 一个包含问卷内容的list封装数据
     */

}
