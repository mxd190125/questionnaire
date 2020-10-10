package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.UserDao;
import com.cdu.questionnaire.pojo.Field;
import com.cdu.questionnaire.pojo.FieldValue;
import com.cdu.questionnaire.pojo.Question;
import com.cdu.questionnaire.pojo.UserSubmit;
import com.cdu.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Version 1.0
 * @Author 马雪冬
 * @Date 2020/9/27 20:43
 * @Description 用户问卷操作业务层
 * Modification User:
 * Modification Date:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserSubmit> findSubQuestionNaires(String userName , int isSub) {
        return userDao.findSubQuestionNaires(userName , isSub);
    }

    @Override
    public List<Question> findUnSubQuestionNaires(String userName, int isSub) {
        return userDao.findUnSubQuestionNaires(userName, isSub);
    }

    @Override
    public List<Map<String, Object>> getQuestionNaireFields(int quesId) {
        List<Field> fieldList=new ArrayList<Field>();
        fieldList=userDao.getQuestionNaireFields(quesId);

        List<Map<String, Object>> list = new ArrayList<>();
        for (Field field : fieldList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", field.getId());
            map.put("fieldName", field.getFieldName());

            String chosse = field.getChosse();
            if (chosse != null){
                String[] chosseArray = chosse.split("#");
                map.put("chosse", chosseArray);
            }else {
                map.put("chosse", chosse);
            }

            map.put("quesId", field.getQuesId());

            String chosseType = field.getChosseType();
            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseTypeArray);

            list.add(map);
        }

        return list;
    }

    @Override
    public Question getQuestionNaire(int quesId) {
        Question question=new Question();
        question=userDao.getQuestionNaire(quesId);
        return question;
    }

    @Override
    public List<Map<String, Object>> getFieldValues(String userName, int quesId) {
        List<FieldValue> fieldValues=new ArrayList<>();
        fieldValues=userDao.getFieldValues(userName, quesId);

        List<Map<String, Object>> list = new ArrayList<>();
        for (FieldValue fieldValue : fieldValues) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", fieldValue.getId());
            map.put("fieldName", fieldValue.getFieldName());
            String chosse = fieldValue.getChosse();
            if (chosse != null){
                String[] chosseArray = chosse.split("#");
                map.put("chosse", chosseArray);
            }else {
                map.put("chosse", chosse);
            }
            map.put("quesId", fieldValue.getQuesId());
            String chosseType = fieldValue.getChosseType();
            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseTypeArray);
            String chosseValue = fieldValue.getChosseValue();
            String[] chosseValueArray = chosseValue.split("#");
            map.put("chosseValue" , chosseValueArray);

            list.add(map);
        }
        return list;
    }

    @Override
    public String getSubmitTime(String userName, int quesId) {
        return userDao.getSubmitTime(userName, quesId);
    }
}
