package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.DoctorDao;
import com.cdu.questionnaire.pojo.*;
import com.cdu.questionnaire.pojo.tmpObject.QuestionnaireInfo;
import com.cdu.questionnaire.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author 汪辉
 * @Date 2020/12/4 17:42
 * @Description :
 */
@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;


    /**
     * 查询该医生所有病人提交的问卷
     *
     * @param doctorId
     * @return 问卷集合
     */
    @Override
    public List<QuestionnaireInfo> queryAllQuestionnaires(Integer doctorId) {
        List<QuestionnaireInfo> questionnaireList = doctorDao.selectAllQuestionnaires(doctorId);
        for (QuestionnaireInfo questionnaire : questionnaireList) {
            questionnaire.setName(doctorDao.selectQuestionnaireName(questionnaire.getQuesId()));
        }
        return questionnaireList;
    }

    @Override
    public List<Map<String, Object>> queryMessage(int doctorId) {
        return doctorDao.selectMessage(doctorId);
    }


//    /**
//     * 返回该问卷的得分
//     *
//     * @param subId
//     * @return
//     */
//    @Override
//    public int queryQuestionnairesScore(Integer subId) {
//        return doctorDao.selectQuestionnaireScore(subId);
//    }

    @Override
    public Question queryQuestion() {
        return doctorDao.selectQuestion();
    }

}
