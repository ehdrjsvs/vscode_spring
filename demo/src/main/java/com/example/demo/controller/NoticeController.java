package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.NoticeLogic;

import jakarta.servlet.http.HttpServletRequest;

//스프링에서는 URL매핑이 4번을(/) 디폴트로하고있다
//서블릿컨테이너와는 다르게 메소드마다 URL지정할수있다
//URL요청 -요청방식 -GET,POST(바이너리-첨부파일처리가능),PUT,DLEECT-전송방식차이
//@Controller는 클래스 선언 앞에온다 - 페이지 출력일때사용한다.
//만일@Controller가 있는 클래스에서 JSON포맷을 예외적으로 사용하고 싶다면 
//@ResponseBody사용함 -> 문자열덩어리?,JSON포맷형식일때 
//RestController @Controller와 같이 컨트롤 계층을 지원하는 어노테이션
//처리결과가 페이지가 아닌경우 사용
//프론트계층(Reactjs사용)과 백엔드 계층 분리
//@RequestMapping은 클래스,메소드이름앞에도 사용가능하다 
//최근에는@GetMapping,@PostMapping지원함에따라서 선언앞에주로사용
//@RequestParam은 메소드의 파라미터자리에 사용되는 어노테이션이다.
//@Service은 서비스계층(MVC패턴:모델계층-처리-기능-비즈니스로직+퍼시스턴스계층)
//DB연동(퍼시스턴스계층)을위한 클래스를 따로 설계추천함 - myBatis,hibernate 라이브러리,프레임워크지원되므로
@Controller()
@RequestMapping("/notice/*")
public class NoticeController {
    Logger logger = LoggerFactory.getLogger(NoticeController.class);
    @Autowired
    NoticeLogic noticeLogic = null;

    // 전체조회 및 조건검색일때
    // SELECT*FROM notice WHERE gubun=> AND keywoed=?
    // spring에서는 더이상 서블릿이아니더라도 단독으로 서비스를 제공할수있도록 발전됨.
    // 서블릿 - 상속은 결합도가 높다 메소드 오버라이드 - 강제사항
    // 그런데 스프링은 결합도를 낮춘다 - 더이상HttpServletRequset,HttpServletResponse를 사용하지않는다
    // req.setAttribute(),req.getParameter()
    //사용자로부터 입력받는값을 읽어오기 - @RequestParam 지원됨
    //Post방식에서 body로 받아올때 @RequestBody사용된다
    @GetMapping("noticeList")
    public String noticeList(Model model, @RequestParam Map<String, Object> pmap) {
        logger.info("noticeList");
        // logger.info(pmap.get("gubun").toString());
        // logger.info(req.getParameter("gubun"));
        // logger.info(pmap.get("keyword").toString());
        List<Map<String, Object>> nList = null;// [ {},{},{} ]
        nList = noticeLogic.noticeList(pmap);
        model.addAttribute("nList", nList);
        return "forward:noticeList.jsp"; // webapp아래에서찾음
    }

    // 포스트 방식은 단위테스트가 안된다 get방식으로 진행
    // insert into notice(n_no,n_title,n_content,n_writer) values(?,?,?,?)
    @PostMapping("noticeInsert")
    public String noticeInsert(@RequestParam Map<String, Object> pmap) {
        logger.info("noticeInsert");
        // logger.info(pmap.get("n_title").toString());
        // logger.info(pmap.get("n_content").toString());
        int result = 0;
        String path = "";
        result = noticeLogic.noticeInsert(pmap);
        if (result == 1) {// 입력이 성공했을때
            path = "redirect:noticeList";
        } else {// 입력이 실패했을때
            path = "redirect:noticeError.jsp";
        }
        return path; // 화면을 호출하는게아니라 url을호출한다-9번라인
    }

    // update notice set n_title=? , n_content=? ,n_writer=> where n_no=?
    @GetMapping("noticeUpdate")
    public String noticeUpdate(@RequestParam Map<String, Object> pmap) {
        logger.info("noticeUpdate");
        int result = 0;
        String path = "";
        result = noticeLogic.noticeUpdate(pmap);
        if (result == 1) {
            path = "redirect:noticeList";
        } else {
            path = "redirect:noticeError.jsp";
        }
        return path; // 화면을 호출하는게아니라 url을호출한다-9번라인
    }

    // delete from notice where n_no=?
    @DeleteMapping("noticeDelete")
    // public String noticeDelete(@RequestParam int n_no) {
    public String noticeDelete(@RequestParam Map<String, Object> pmap) {
        logger.info("noticeDelete");
        int result = 0;
        String path = "";
        result = noticeLogic.noticeDelete(pmap);
        if (result == 1) {
            path = "redirect:noticeList";
        } else {
            path = "redirect:noticeError.jsp";
        }
        return path; // 화면을 호출하는게아니라 url을호출한다-9번라인
    }
}
