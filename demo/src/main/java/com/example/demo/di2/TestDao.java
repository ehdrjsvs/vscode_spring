package com.example.demo.di2;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDao {
    Logger logger = LoggerFactory.getLogger(TestDao.class);

    public List<Map<String, Object>> testList() {
        System.out.println("TestDao testList()호출");
        return new ArrayList<>();
    }
}