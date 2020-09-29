package com.cdu.questionnaire.service;

import com.cdu.questionnaire.pojo.Field;
import com.cdu.questionnaire.pojo.FieldValue;
import com.cdu.questionnaire.pojo.Question;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    public List<Question> findQuestionNaires(String userName , int isSub);

    public List<Map<String, Object>> getQuestionNaireFields(int quesId);

    public Question getQuestionNaire(int quesId);

    public List<Map<String, Object>> getFieldValues(String userName , int quesId);

    public String getSubmitTime(String userName , int quesId);

}
