package com.cdu.questionnaire.dao;

import com.cdu.questionnaire.pojo.*;
import com.cdu.questionnaire.pojo.tmpObject.QuestionnaireInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author 汪辉
 * @Date 2020/12/4 17:40
 * @Description : 医生dao
 */
@Mapper
public interface DoctorDao {

    List<QuestionnaireInfo> selectAllQuestionnaires(Integer doctorId);

    String selectQuestionnaireName(Integer quesId);

    int selectQuestionnaireScore(Integer subId);

    int selectQuestionnaireIdBySubId(String subId);

    Question selectQuestion();

    List<Map<String,Object>> selectMessage(int doctorId);

}
