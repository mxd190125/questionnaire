package com.cdu.questionnaire.controller;

import com.cdu.questionnaire.pojo.tmpObject.QuestionnaireInfo;
import com.cdu.questionnaire.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 汪辉
 * @Date 2020/12/4 17:04
 * @Description :  医生Controller
 */
@RestController
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    /**
     * 查询该医生所有病人的问卷提交和留言情况
     *
     * @param doctorId 医生id
     * @return 所有问卷信息
     */
    @GetMapping("/doctorowAllQuestionnaires")
    public Map<String, Object> showAllQuestionnaires(int doctorId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "1");
        //问卷列表
        List<QuestionnaireInfo> questionnaireList = doctorService.queryAllQuestionnaires(doctorId);
        map.put("questionnaireList",questionnaireList);
        //留言的列表
        List<Map<String, Object>> messages = doctorService.queryMessage(doctorId);
        map.put("message",messages);
        return map;
    }


//    /**
//     * 查询某一问卷得分
//     *
//     * @param subId 问卷id
//     * @return 问卷得分
//     */
//    @GetMapping("/doctor/showQuestionnaireScore")
//    public HashMap<String , Object> showQuestionnaireScore(int subId) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("status" , "1");
//        return doctorService.queryQuestionnairesScore(subId);
//    }

}
