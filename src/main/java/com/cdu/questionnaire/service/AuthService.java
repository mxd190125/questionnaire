package com.cdu.questionnaire.service;

import com.cdu.questionnaire.pojo.User;

public interface AuthService {

    public User login(String userName);

    public int deleteUnValidUser(String userName , int isRegister);

    public int isRegisterFail(String userName);

    public int isExits(String userName);

    public int statusOfRegister(String userName);

}
