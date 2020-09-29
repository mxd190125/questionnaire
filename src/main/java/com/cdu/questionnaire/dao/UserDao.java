package com.cdu.questionnaire.dao;

import com.cdu.questionnaire.pojo.Field;
import com.cdu.questionnaire.pojo.FieldValue;
import com.cdu.questionnaire.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获得未提交问卷集合的信息
     * @param userName
     * @return
     */
    public List<Question> findQuestionNaires(@Param("userName") String userName , @Param("isSub") int isSub);

    /**
     * 获得指定问卷的内容
     * @param
     * @param quesId
     * @return
     */
    public List<Field> getQuestionNaireFields(@Param("quesId") int quesId);

    /**
     * 获得指定问卷信息
     * @param quesId
     * @return
     */
    public Question getQuestionNaire(@Param("quesId") int quesId);

    /**
     * 获取用户已提交的指定问卷选取的答案
     * @param userName
     * @param quesId
     * @return
     */
    public List<FieldValue> getFieldValues(@Param("userName") String userName , @Param("quesId") int quesId);

    /**
     * 获取提交问卷的时间
     * @param userName
     * @param quesId
     * @return
     */
    public String getSubmitTime(@Param("userName") String userName , @Param("quesId") int quesId);
}
