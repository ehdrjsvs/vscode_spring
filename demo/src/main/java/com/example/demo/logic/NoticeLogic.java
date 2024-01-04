package com.example.demo.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NoticeDao;

@Service
public class NoticeLogic {
    Logger logger = LoggerFactory.getLogger(NoticeLogic.class);

    @Autowired
    NoticeDao noticeDao = null;

    // 조건검색일때
    public List<Map<String, Object>> noticeList(Map<String, Object> pmap) {
        List<Map<String, Object>> list = new ArrayList<>();
        // NoticeDao클래스앞에 @Service 어노테이션을 붙여주면 applicationcontext에서 관리를해주어 @Autowired로
        // 의존성주입 가능하다
        list = noticeDao.noticeList(pmap);
        return list;
    }// end of noticeList

    // 등록할때 -insert
    public int noticeInsert(Map<String, Object> pMap) {
        logger.info("noticeInsert");
        int result = 0;
        result = noticeDao.noticeInsert(pMap);
        return result;
    }// end of noticeInsert

    // 수정할때 -update
    public int noticeUpdate(Map<String, Object> pMap) {
        logger.info("noticeUpdate");
        int result = 0;
        result = noticeDao.noticeUpdate(pMap);
        return result;
    }//////////// end of noticeUpdate

    // 삭제일때 -delete
    public int noticeDelete(Map<String, Object> pMap) {
        logger.info("noticeDelete");
        int result = 0;
        result = noticeDao.noticeDelete(pMap);
        return result;
    }///////////// end of noticeDelete
}
