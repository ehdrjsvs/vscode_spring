package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("noticeList", pmap);
        logger.info(list.toString());
        return list;
    }
}
