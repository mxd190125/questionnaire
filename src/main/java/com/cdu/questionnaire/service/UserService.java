package com.cdu.questionnaire.service;

import com.cdu.questionnaire.pojo.Message;
import com.cdu.questionnaire.pojo.Question;
import com.cdu.questionnaire.pojo.UserSubmit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    public List<UserSubmit> findSubQuestionNaires(String userName , int isSub);

    public List<Question> findUnSubQuestionNaires(String userName , int isSub);

    public List<Map<String, Object>> getQuestionNaireFields(int quesId);

    public Question getQuestionNaire(int quesId);

    public List<Map<String, Object>> getFieldValues(String userName , int quesId);

    public String getSubmitTime(String userName , int quesId);

//    public List<UnField> getUnFileds(int fieldId);

    /**
     * 第二阶段开发的代码
     */

    public HashMap<String , Object> getUnSubAnswerInfo(int userId);

    public List<Map<String, Object>> getUnSubAnswerFields(int quesId);

    public HashMap<String , Object> getUnSubPSAInfo(int userId);

    public List<HashMap<String , Object>> getMessageByUser(int userId , int senderId);

    public HashMap<String , Object> getDoctorIdAndUserIdByUserName(String userName);

    public List<HashMap<String , Object>> getSubAnswerFieldsAndValue(int quesId , int subId);

    public HashMap<String , Object> getPsaValue( int subId);

    public boolean insertIntoPSA(String photoUrl , String selectTime , int subId);

    public boolean insertIntoAnswer(List<HashMap<String , Object>> listOne , List<HashMap<String , Object>> listSecond , int subScore , int subId);

    public List<HashMap<String , Object>> findAllSubQuestionnaires(int userId);

}
