package com.cdu.questionnaire.service.impl;

import com.cdu.questionnaire.dao.SubQuesDao;
import com.cdu.questionnaire.service.SubQuesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SubQuesServiceImpl
 * @Version 1.0
 * @Author 何政梁
 * @Date 2020/9/28 15:22
 * @Description TODO
 * Modification User:
 * Modification Date:
 */
@Service
public class SubQuesServiceImpl implements SubQuesService {

    @Resource
    private SubQuesDao subQuesDao;

    @Override
    public String queryId(String userName) {
        return subQuesDao.queryId(userName);
    }

    @Override
    public Integer recordSub(String userId, String quesId) {
        return subQuesDao.recordSub(userId,quesId);
    }

    @Override
    public Integer checkIsSub(String userId, String quesId) {
        return subQuesDao.checkIsSub(userId,quesId);
    }

    @Override
    public Integer writeConQues(List<Map<String, Object>> mapList) {
        return subQuesDao.writeConQues(mapList);
    }
}
