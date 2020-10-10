package com.cdu.questionnaire.controller;


import com.cdu.questionnaire.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Version 1.0
 * @Author 何政梁
 * @Date 2020/9/27 17:14
 * @Description 管理操作功能
 * Modification User:
 * Modification Date:
 */

@Slf4j
@RestController
public class AdminController {

    /**
     * 分页查询，每页显示最大数
     */
    private static final int PAGE_SIZE = 5;

    /**
     * JSON转字符串格式
     */
    ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private AdminService adminService;

    /**
     *查询待审核用户信息
     * @return 一个包含所有待审核用户的List格式字符串
     * @throws JsonProcessingException 抛出错误
     */
    @GetMapping(value = "/admin/home/userManage")
    public String queryUser() throws JsonProcessingException {
    List<String> userList = adminService.queryUser();
    String result = objectMapper.writeValueAsString(userList);
        if (userList != null){
            return  "{\"status\":\"1\""+ ",\"userList\":" + result + "}";
        }else {
            return  "{\"status\":\"0\""+ ",\"userList\":" + null + "}";
        }
    }

    /**
     *  通过用户注册，改变数据库用户状态
     * @param userName 用户id
     * @return 返回通过状态码
     */
    @GetMapping(value = "/admin/home/agreeUser")
    public String agreeAuditUser(String userName){
        //查询待审核用户是否存在
        int status = adminService.queryAuditUser(userName);
        if (status !=0){
            //通过审核
            Integer auditStatus = adminService.agreeAuditUser(userName);
            //查询未填写的问卷
            List<Map<String,Object>> list = adminService.queryQuesTime(userName);
            //关联未填写问卷
           Integer integer = adminService.writeQuesById(list);
            if (auditStatus != 0 && integer != 0){
                    return  "{\"status\":\"1\"}";
                }else {
                  return  "{\"status\":\"0\"}";
                }
        }else {
            return  "{\"status\":\"0\"}";
        }
    }

    /**
     *  拒绝用户注册，改变数据库用户状态
     * @param userName 用户id
     * @return 返回拒绝状态码
     */
    @GetMapping(value = "/admin/home/refuseUser")
    public String refuseAuditUser(String userName){
        int userInteger = adminService.queryAuditUser(userName);
        if (userInteger != 0){
            Integer auditStatus = adminService.refuseAuditUser(userName);
            if (auditStatus != 0){
                return  "{\"status\":\"1\"}";
            }else {
                return  "{\"status\":\"0\"}";
            }
        }else {
            return  "{\"status\":\"0\"}";
        }
    }

    /**
     * * 显示所有的用户
     * @return 返回一个所有正式用户的List集合数据
     * @throws JsonProcessingException 抛出错误
     */
    @PostMapping(value = "/admin/home/showAllUser")
    public String showAllUser() throws JsonProcessingException {
        List<String> userList = adminService.queryAllUser();
        String result = objectMapper.writeValueAsString(userList);
        if (userList != null){
            return  "{\"status\":\"1\""+ ",\"userList\":" + result + "}";
        }else {
            return  "{\"status\":\"0\""+ ",\"userList\":" + null + "}";
        }
    }

    /**
     * 动态搜索用户
     * @param dynamicId 用户模糊id
     * @return 返回模糊搜索结果
     */
    @GetMapping(value = "/admin/home/dynamicId")
    public String searchUser(String dynamicId) throws JsonProcessingException {
        List<String> searchUser = adminService.searchUser(dynamicId);
        String result = objectMapper.writeValueAsString(searchUser);
        if (result != null){
            return  "{\"status\":\"1\""+ ",\"userList\":" + result + "}";
        }else {
            return  "{\"status\":\"0\""+ ",\"userList\":" + null + "}";
        }
    }

    /**
     *  点击指定用户，返回该用户填写的所有问卷名字集合
     * @param pageNumber 页数
     * @param userName 用户id
     * @return 该用户的所有问卷集合
     * @throws JsonProcessingException 抛出错误
     */
    @GetMapping(value = "/admin/home/userQues")
    public String queryQuesByUser(Integer pageNumber,String userName) throws JsonProcessingException {
        List<String> quesList = adminService.queryQuesByUser((pageNumber-1)*PAGE_SIZE,userName);
        String result = objectMapper.writeValueAsString(quesList);
        if (quesList != null){
            return  "{\"status\":\"1\""+ ",\"quesList\":" + result + "}";
        }else {
            return  "{\"status\":\"0\""+ ",\"quesList\":" + null + "}";
        }
    }


}
