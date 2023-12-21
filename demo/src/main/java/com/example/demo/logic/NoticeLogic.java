package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Map<String, Object>> noticeList(Map<String, Object> pmap) {
        List<Map<String, Object>> list = new ArrayList<>();
        // NoticeDao클래스앞에 @Service 어노테이션을 붙여주면 applicationcontext에서 관리를해주어 @Autowired로
        // 의존성주입 가능하다
        list = noticeDao.noticeList(pmap);
        return list;
    }

}
