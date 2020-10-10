package com.cdu.questionnaire.dao;

import com.cdu.questionnaire.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthDao {

    /**
     * @param userName
     * @return
     */
    public User login(@Param("userName") String userName);

    /**
     * @param userName
     * @return
     */
    public int deleteUnValidUser(@Param("userName") String userName , @Param("isRegister") int isRegister);

    /**
     *
     * @param userName
     * @return
     */
    public int isRegisterFail(@Param("userName") String userName);

    /**
     *
     * @param userName
     * @return
     */
    public int isExits(@Param("userName") String userName);

    /**
     *
     * @param userName
     * @return
     */
    public int statusOfRegister(@Param("userName") String userName);

    /**
     *
     * @param userName
     * @return
     */
    public void registerUserName(@Param("_userName") String userName);

}
