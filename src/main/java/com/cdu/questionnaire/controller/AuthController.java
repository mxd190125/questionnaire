package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.pojo.User;
import com.cdu.questionnaire.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName LoginController
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/27 16:58
 * @Description 认证接口
 * Modification User:
 * Modification Date:
 */
@Slf4j
@RestController
public class AuthController {

    private static final String STATUS_SUCCESS="1";
    private static final String STATUS_UN_SUCCESS="0";

    private static final String RE_ING="1";
    private static final String RE_SUCCESS="2";
    private static final String RE_FAIL="3";
    private static final String RE_NULL="4";

    @Resource
    private AuthService authService;

    /**
     * 用户/管理员登录
     * @param userName
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/auth/login")
    public String login(String userName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result="";

        if (userName!=null && userName.length()>0){
            int isExits=authService.isExits(userName);

            if (isExits!=0){
                User user=authService.login(userName);
                log.info("################"+mapper.writeValueAsString(user));

                if (authService.isRegisterFail(userName)==1){
                    //注册失败
                    result="{\"status\":"+STATUS_UN_SUCCESS+
                            ",\"reStatus\":\""+RE_FAIL+
                            "\",\"role\":\"-1\"}";
                    //删除注册失败的用户
                    authService.deleteUnValidUser(userName , 2);
                }else if (user==null){
                    //正在注册
                    result="{\"status\":"+STATUS_UN_SUCCESS+
                            ",\"reStatus\":\""+RE_ING+
                            "\",\"role\":\"-1\"}";
                }else if (user.getIsRegister()==1){
                    //成功注册
                    result="{\"status\":"+STATUS_SUCCESS+
                            ",\"reStatus\":\""+RE_SUCCESS+
                            "\",\"role\":\""+user.getRole()+"\"}";
                }
            }else {
                //未注册
                result="{\"status\":"+STATUS_UN_SUCCESS+
                        ",\"reStatus\":\""+RE_NULL+
                        "\",\"role\":\"-1\"}";
            }
        }

        return result;
    }


    /**
     * 用户注册
     * @param userName
     * @return
     */
    @PostMapping(value = "/auth/register")
    public String register(String userName){
        ObjectMapper mapper = new ObjectMapper();
        String result="";

        if (userName!=null && userName.length()>0){
            int isExits=authService.isExits(userName);
            if (isExits!=0){
                int statusOfRegister=authService.statusOfRegister(userName);
                //用户已成功注册(存在)，可以登录
                if (statusOfRegister==1){
                    result="{\"status\":"+STATUS_UN_SUCCESS+
                            ",\"reStatus\":\""+RE_SUCCESS+"\"}";
                }else if (statusOfRegister==0){
                    //用户正在注册,等待管理员审核
                    result="{\"status\":"+STATUS_UN_SUCCESS+
                            ",\"reStatus\":\""+RE_ING+"\"}";
                }else if (statusOfRegister==2){
                    //已申请过注册，注册失败
                    result="{\"status\":"+STATUS_UN_SUCCESS+
                            ",\"reStatus\":\""+RE_FAIL+"\"}";
                    //删除注册失败的用户
                    authService.deleteUnValidUser(userName , 2);
                }
            }else {
                authService.registerUserName(userName);
                //用户未注册
                result="{\"status\":"+STATUS_SUCCESS+
                        ",\"reStatus\":\""+RE_NULL+"\"}";
            }
        }

        return result;
    }
}
