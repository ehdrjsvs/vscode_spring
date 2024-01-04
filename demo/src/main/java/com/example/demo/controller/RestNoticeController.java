package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logic.NoticeLogic;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@RestController // 화면없이 조회결과를 문자열 포맷으로 처리할때 사용 -@ResponseBody대체로 제공됨
@RequestMapping("/notice/*")
public class RestNoticeController {
    Logger logger = LoggerFactory.getLogger(RestNoticeController.class);
    @Autowired
    NoticeLogic noticeLogic = null;

    // @GetMapping 일때 사용자가입력한 값을 받을때는 @RequestParam 사용함
    // @PostMapping 일때 -@RequestBody 사용 - 리액트
    @GetMapping("jsonNoticeList")
    public String jsonNoticeList(@RequestParam Map<String, Object> pmap, HttpServletRequest req) {
        // http://localhost:8000/notice/jsonNoticeList?gubun=n_title&keyword=휴관
        logger.info(pmap.toString());// n_title,n_content,n_writer , keyword=휴관
        List<Map<String, Object>> list = null;
        list = noticeLogic.noticeList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);// 파라미터로받은 List<Map<>>형태를 JSON형식으로 전환해줌
        return temp;
    }

    @PostMapping("noticeInsert2")
    public String noticeInsert2(@RequestBody Map<String, Object> pmap) {
        logger.info("noticeInsert2");
        // logger.info(pmap.get("n_title").toString());
        // logger.info(pmap.get("n_content").toString());
        logger.info(pmap.toString());
        int result = 0;
        result = noticeLogic.noticeInsert(pmap);
        return String.valueOf(result);// 성공1 ,실패0
    }

}
