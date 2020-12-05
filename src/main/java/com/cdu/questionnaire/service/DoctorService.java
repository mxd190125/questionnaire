package com.cdu.questionnaire.service;


import com.cdu.questionnaire.pojo.Question;
import com.cdu.questionnaire.pojo.tmpObject.QuestionnaireInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author 汪辉
 * @Date 2020/12/4 17:41
 * @Description :
 */
public interface DoctorService {


    /**
     * 查询该医生所有病人提交的问卷
     * @param doctorId
     * @return 问卷集合
     */
    List<QuestionnaireInfo> queryAllQuestionnaires(Integer doctorId);

    List<Map<String, Object>> queryMessage(int doctorId);


//    /**
//     * 返回该问卷的得分
//     * @param subId
//     * @return
//     */
//    int queryQuestionnairesScore(Integer subId);

    Question queryQuestion();


}
