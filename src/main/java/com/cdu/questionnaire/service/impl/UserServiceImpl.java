package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.UserDao;
import com.cdu.questionnaire.pojo.*;
import com.cdu.questionnaire.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseType);
            if (chosseType.equals("6")){
                map.put("unField",userDao.getUnFields(field.getId()));
            }
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
//            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseType);
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

    /**
     * 第二阶段开发的代码
     */
    @Override
    public HashMap<String, Object> getUnSubAnswerInfo(int userId) {
        return userDao.getUnSubAnswerInfo(userId);
    }

    @Override
    public List<Map<String, Object>> getUnSubAnswerFields(int quesId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Field> fieldList = userDao.getAnswerField(quesId);
        for (Field field : fieldList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", field.getId());
            map.put("fieldName", field.getFieldName());

            String chosse = field.getChosse();
            String score = field.getScore();
            if (chosse != null){
                String[] chosseArray = chosse.split("#");
                map.put("chosse", chosseArray);
                String[] scoreArray = score.split("#");
                map.put("score", scoreArray);
            }else {
                map.put("chosse", chosse);
                map.put("score", score);
            }

            map.put("quesId", field.getQuesId());

            String chosseType = field.getChosseType();
//            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseType);
            if (chosseType.equals("6")){
                map.put("unField",userDao.getUnFields(field.getId()));
            }
            list.add(map);

        }
        return list;
    }

    @Override
    public HashMap<String, Object> getUnSubPSAInfo(int userId) {
        HashMap<String, Object> map = userDao.getUnSubPSAInfo(userId);
        return map;
    }

    @Override
    public List<HashMap<String, Object>> getMessageByUser(int userId , int senderId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (int subId : userDao.getSubIdByuser(userId)) {
            list.add(userDao.getMessageByUser(subId, senderId));
        }
        return list;
    }

    @Override
    public HashMap<String, Object> getDoctorIdAndUserIdByUserName(String userName) {
        return userDao.getDoctorIdAndUserIdByUserName(userName);
    }

    @Override
    public List<HashMap<String, Object>> getSubAnswerFieldsAndValue(int quesId, int subId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<Field> fieldList = userDao.getAnswerField(quesId);
        for (Field field : fieldList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", field.getId());
            map.put("fieldName", field.getFieldName());

            String chosse = field.getChosse();
            String score = field.getScore();
            if (chosse != null){
                String[] chosseArray = chosse.split("#");
                map.put("chosse", chosseArray);
                String[] scoreArray = score.split("#");
                map.put("score", scoreArray);
            }else {
                map.put("chosse", chosse);
                map.put("score", score);
            }

            map.put("quesId", field.getQuesId());

            String chosseType = field.getChosseType();
//            String[] chosseTypeArray = chosseType.split("#");
            map.put("chosseType", chosseType);
            if (chosseType.equals("6")){
                List<HashMap<String, Object>> unFields = userDao.getUnFields(field.getId());
                map.put("unField",unFields);

                List<String> unFieldValues = userDao.getUnFieldValue(unFields , subId);
                map.put("value" , unFieldValues);
            }else {
                String filedValue = userDao.getFieldValue(field.getId(), subId);
                map.put("value" , filedValue.split("#"));
            }

            list.add(map);
        }
        return list;
    }

    @Override
    public HashMap<String, Object> getPsaValue(int subId) {
        return userDao.getPsaValue(subId);
    }

    @Transactional(rollbackFor = {RuntimeException.class , Exception.class})
    @Override
    public boolean insertIntoPSA(String photoUrl, String selectTime, int subId) {
        int i = userDao.insertIntoPSA(photoUrl, selectTime, subId, 2);
        int j = userDao.updateUserQues(subId, 0);
        if (i > 0 && j>0){
            return true;
        }else {
            try {
                throw new RuntimeException("更新提交记录失败，进行回滚！：insertIntoPSA" );
            } catch (RuntimeException e) {
                e.printStackTrace();
            }finally {
                return false;
            }
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class , Exception.class})
    @Override
    public boolean insertIntoAnswer(List<HashMap<String, Object>> listOne, List<HashMap<String, Object>> listSecond , int subScore , int subId) {
        boolean c = userDao.updateUserQues(subId , subScore)>0;
        boolean a = listOne==null || (listOne!=null && userDao.insertIntoAnswerOne(listOne)>0);
        boolean b = listSecond==null || (listSecond!=null && userDao.insertIntoAnswerSecond(listSecond)>0);
        if (a&&b&&c){
            return true;
        }else {
            try {
                throw new RuntimeException("更新提交记录失败，进行回滚！：insertIntoAnswer" );
            } catch (RuntimeException e) {
                e.printStackTrace();
            }finally {
                return false;
            }
        }
    }

    @Override
    public List<HashMap<String, Object>> findAllSubQuestionnaires(int userId) {
        return userDao.findAllSubQuestionnaires(userId);
    }

}
