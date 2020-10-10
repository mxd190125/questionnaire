package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.AuthDao;
import com.cdu.questionnaire.pojo.User;
import com.cdu.questionnaire.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName AuthServiceImpl
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/27 17:08
 * @Description 认证接口业务类
 * Modification User:
 * Modification Date:
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Override
    public User login(String userName) {
        User user = new User();
        user=authDao.login(userName);
        return user;
    }

    @Override
    public int deleteUnValidUser(String userName, int isRegister) {
        return authDao.deleteUnValidUser(userName, isRegister);
    }

    @Override
    public int isRegisterFail(String userName) {
        int count=0;
        count=authDao.isRegisterFail(userName);
        return count;
    }

    @Override
    public int isExits(String userName) {
        return authDao.isExits(userName);
    }

    @Override
    public int statusOfRegister(String userName) {
        return authDao.statusOfRegister(userName);
    }

    @Override
    public void registerUserName(String userName) {
        authDao.registerUserName(userName);
    }
}
