package com.example.demo.restfull;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vo.MemberVO;

@RestController
@RequestMapping("/http/*")
public class RestfullController {
    Logger logger = LoggerFactory.getLogger(RestfullController.class);

    // 테스트방법
    // //http://localhost:8000/http/get?mem_id=kiwi&mem_name=키위
    @GetMapping("get")
    public String getTest(MemberVO mVO) {
        return "get요청 : " + mVO.getMem_id() + "," + mVO.getMem_name();
    }

    // URL요청이름 == 메소드이름 ==화면이름
    // :왜 맞춰야해요? - 디버깅 - 추척 - 같은이름:혼동:라인번호,시간
    // demo_member테이블이름 = VO이름 = 제네릭 맵 사용시 키값
    // 테스트방법
    // //http://localhost:8000/http/lombokTest
    @GetMapping("lombokTest")
    public String lombokTest(MemberVO mVO) {
        MemberVO mVO2 = MemberVO.builder().mem_id(mVO.getMem_id()).mem_no(mVO.getMem_no()).build();
        return "lombokTest  : " + mVO2.getMem_id() + "," + mVO2.getMem_name() + "||" + mVO2.getMem_no();
    }

    @PostMapping("post")
    public String postTest(@RequestBody MemberVO mVO) {
        return "post요청 : " + mVO.getMem_id() + "," + mVO.getMem_name();
    }

    @PostMapping("post1")
    public String postTest1(@RequestParam Map<String, Object> pMap) {
        return "post1요청 : " + pMap.get("mem_id") + "," + pMap.get("mem_name");
    }

    @PutMapping("update/{id}")
    public MemberVO update(@PathVariable int id) {
        logger.info(String.valueOf(id));
        MemberVO mVO = new MemberVO();
        mVO.setMem_id("nice");
        return mVO;
    }

    @DeleteMapping("delete/{no}")
    public String delete(@PathVariable int no) {
        logger.info(String.valueOf(no));
        return String.valueOf(no);
    }
}

/*
 * 1.Get요청 (select시 사용)
 * :주소에 데이터를 담아보낸다
 * 데이터형태는 key=value이다
 * 
 * 2.Post,Put,Delete 요청
 * :Body에 담아서 보낸다 데이터형태는 json으로 통일
 * form태그 요청은 get,post 요청만 가능
 * :put,delete요청은 js로 요청함 - 테스트가 번거로움
 * 
 * 화면 템플릿엔진 ,ui솔루션 리액트사용시
 * js로 요청해야함 ajax,fetch , axios - 비동기처리 지원 API
 * 자바스크립트는 동기가 디폴트이다
 * 
 * 
 * 
 * 
 * 
 */