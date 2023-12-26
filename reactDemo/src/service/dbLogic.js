import axios from "axios";
//spring 프로젝트와 연계하기
//공지사항 조회하기 - select
//자바스크립트에서 좌중괄호와 우중괄호가 있으면 객체이다.
//const notice = {gubun:'n_title' or 'n_writer' or 'n_content' , keyword:'휴관'}
//ES6 - 2015 - module = mdn module:export 
//const 상수 , let 가변 : 선언시 사용됨 
//컴파일을 하지않는 언어, 런타임시 결정됨 
//자바스크립트는 기본적으로 동기이다 -비동기처리가 필요하다 
export const noticeListDB = (notice) => {
  return new Promise((resolve, reject) => {//성공이면resolve로 값을받아냄,실패시 reject에러처리
    try {
      console.log(notice);//브라우저 개발자도구 - 사용자가 입력한 n_title,n_content,n_writer , keyword
      //axios - 비동기 요청 처리 ajax - fetch(브라우저) - axios(NodeJS- oracle서버연동)
      const response = axios({//비동기처리(스프링플젝-8000연동)을위한 axios
        //3000번 서버에서 8000서버로 요청을 함 - 네트워크(다른서버 - CORS이슈)
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "notice/jsonNoticeList",
        params: notice, //쿼리스트링은 header에 담김 - get방식
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const noticeDetailDB = (notice) => {
  return new Promise((resolve, reject) => {
    try {
      console.log(notice);
      //axios - 비동기 요청 처리 ajax - fetch(브라우저) - axios(NodeJS- oracle서버연동)
      const response = axios({
        //3000번 서버에서 8000서버로 요청을 함 - 네트워크(다른서버 - CORS이슈)
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "notice/jsonNoticeList",
        params: notice, //쿼리스트링은 header에 담김 - get방식
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const noticeInsertDB = (notice) => {
  return new Promise((resolve, reject) => {
    try {
      console.log(notice);
      const response = axios({
        method: "post", //@RequestBody
        url: process.env.REACT_APP_SPRING_IP + "notice/noticeInsert2",
        data: notice, //post방식으로 전송시 반드시 data속성으로 파라미터 줄것
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};
export const noticeUpdateDB = (notice) => {
  console.log(notice);
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "post", //@RequestBody
        url: process.env.REACT_APP_SPRING_IP + "notice/noticeUpdate",
        data: notice, //post방식으로 전송시 반드시 data속성으로 파라미터 줄것
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};

export const noticeDeleteDB = (board) => {
  return new Promise((resolve, reject) => {
    try {
      const response = axios({
        method: "get",
        url: process.env.REACT_APP_SPRING_IP + "reple/qnaDelete",
        params: board,
      });
      resolve(response);
    } catch (error) {
      reject(error);
    }
  });
};