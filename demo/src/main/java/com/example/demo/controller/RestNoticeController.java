package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("jsonNoticeList")
    public String jsonNoticeList(@RequestParam Map<String, Object> pmap, HttpServletRequest req) {
        logger.info(pmap.get("gubun").toString());
        logger.info(req.getParameter("gubun"));
        logger.info(pmap.get("keyword").toString());
        List<Map<String, Object>> list = null;
        list = noticeLogic.noticeList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);
        return temp;
    }
}
