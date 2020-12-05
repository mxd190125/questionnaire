package com.cdu.questionnaire.dao;

import com.cdu.questionnaire.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获得提交问卷集合的信息
     * @param userName
     * @return
     */
    public List<UserSubmit> findSubQuestionNaires(@Param("userName") String userName , @Param("isSub") int isSub);

    public List<Question> findUnSubQuestionNaires(@Param("userName") String userName , @Param("isSub") int isSub);

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

    /**
     *
     * @param fieldId
     * @return
     */
    public List<HashMap<String , Object>> getUnFields(@Param("fieldId") int fieldId);


    /**
     * 第二阶段开发的代码
     */

    public HashMap<String , Object> getUnSubAnswerInfo(@Param("userId") int userId);

    public HashMap<String , Object> getUnSubPSAInfo(@Param("userId") int userId);

    public List<Field> getAnswerField(@Param("quesId") int quesId);

    public List<Integer> getSubIdByuser(@Param("userId") int userId);

    public HashMap<String , Object> getDoctorIdAndUserIdByUserName(@Param("userName") String userName);

    public HashMap<String , Object> getMessageByUser(@Param("subId") int subId , @Param("senderId") int senderId);

    public String getFieldValue(@Param("fieldId") int fieldId , @Param("subId") int subId);

    public List<String> getUnFieldValue(@Param("unFieldList") List<HashMap<String , Object>> unFields, @Param("subId") int subId);

    public HashMap<String , Object> getPsaValue(@Param("subId") int subId);

    public int updateUserQues(@Param("subId") int subId , @Param("subScore") int subSscore);

    public int insertIntoPSA(@Param("photoUrl") String photoUrl , @Param("selectTime") String selectTime , @Param("subId") int subId , @Param("quesId") int quesId);

    public int insertIntoAnswerOne(@Param("list") List<HashMap<String , Object>> list);

    public int insertIntoAnswerSecond(@Param("list") List<HashMap<String , Object>> list);

    public List<HashMap<String , Object>> findAllSubQuestionnaires(@Param("userId") int userId);
}
