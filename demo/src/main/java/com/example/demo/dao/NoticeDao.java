package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// xxxDao클래스는 MVC패턴에 영향을주는 클래스는 아니다 - 디자인패턴
//@Service 퍼시스턴스? 모델계층일부? 데이터의 영속성을 처리하는 계층 = 퍼시스턴스계층이다 
//모델계층 (비즈니스로직=업무처리 + 퍼시스턴스계층=서비스계층?)
//@Repository
@Service
public class NoticeDao {
    Logger logger = LoggerFactory.getLogger(NoticeDao.class);
    @Autowired
    SqlSessionTemplate sqlSessionTemplate = null; // SqlSession-myBatis.jar-> myBatis-spring.jar = Applicationcontext

    public List<Map<String, Object>> noticeList(Map<String, Object> pmap) {// gubun=n_title,keyword=휴관
        logger.info("noticeList");
        // JAVA에서 ->myBatis -> Oracle
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("noticeList", pmap);
        logger.info(list.toString());
        return list;
    }

    public int noticeInsert(Map<String, Object> pMap) {
        logger.info("noticeInsert");
        int result = 0;
        result = sqlSessionTemplate.insert("noticeInsert", pMap);
        return result;
    }

    public int noticeUpdate(Map<String, Object> pMap) {
        logger.info("noticeUpdate");
        int result = 0;
        try {
            result = sqlSessionTemplate.update("noticeUpdate", pMap);

        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }//////////// end of noticeUpdate

    public int noticeDelete(Map<String, Object> pMap) {
        logger.info("noticeDelete");
        int result = 0;
        try {
            result = sqlSessionTemplate.delete("noticeDelete", pMap);

        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
    }///////////// end of noticeDelete

}
