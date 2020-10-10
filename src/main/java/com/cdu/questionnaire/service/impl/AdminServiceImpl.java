package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.AdminDao;
import com.cdu.questionnaire.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @ClassName AdminService
 * @Version 1.0
 * @Author 何政梁
 * @Date 2020/9/27 17:18
 * @Description 用户管理业务层
 * Modification User:
 * Modification Date:
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;


    @Override
    public List<String> queryUser() {
        return adminDao.queryUser();
    }

    @Override
    public Integer agreeAuditUser(String userName) {
        return adminDao.agreeAuditUser(userName);
    }

    @Override
    public int queryAuditUser(String userName) {
        return adminDao.queryAuditUser(userName);
    }

    @Override
    public Integer refuseAuditUser(String userName) {
        return adminDao.refuseAuditUser(userName);
    }



    @Override
    public List<String> queryAllUser() {
        return adminDao.queryAllUser();
    }

    @Override
    public List<String> queryQuesByUser(Integer pageNumber,String userName) {
        return adminDao.queryQuesByUser(pageNumber,userName);
    }

    @Override
    public List<String> searchUser(String dynamicId) {
        return adminDao.searchUser(dynamicId);
    }

    @Override
    public List<Map<String,Object>>  queryQuesTime(String userName) {
        return adminDao.queryQuesTime(userName);
    }

    @Override
    public Integer writeQuesById(List<Map<String,Object>> list) {
        return adminDao.writeQuesById(list);
    }

}
