import React, { useEffect, useState } from 'react'
import Header from '../include/Header';
import { Button, Table } from 'react-bootstrap';
import Bottom from '../include/Bottom';
import { useNavigate } from 'react-router';
import NoticeRow from '../notice/NoticeRow';
import { noticeListDB } from '../../service/dbLogic';

const NoticePage = () => {
  console.log('first');
  const navigate = useNavigate();
  const [gubun, setGubun] = useState('')
  const [keyword, setKeyword] = useState('')
  const [notices, setNotices] = useState([{}])
  console.log(notices);
  useEffect(() => {//effect훅이다 - 이름앞에 useXXX형태이면 Hook이다
    console.log('effect');
    noticeList();
  }, [setGubun, setKeyword, setNotices]);//의존성배열이 빈깡통이면 한번만실행됨
  //배열안에 여러가지 상태값이 올수 있다 이상태가 바뀔때마다 매번실행됨
  //async 파라미터앞에 붙인이유는 비동기처리를 위하여 붙음 - 스프링플젝과연계 -await noticeListDB()
  const noticeList = async () => {
    console.log("noticeList호출");
    //document.querySelector = 돔트리에서 아이디값가져옴 .value = n_title,n_writer,n_content
    const gubun = document.querySelector("#gubun").value;
    const keyword = document.querySelector("#keyword").value;/// value = keyword , 제목 내용 작성자
    console.log(`${gubun},${keyword}`);
    const notice = {//괄호가있는것 - 객체이다 
      gubun: gubun,
      keyword: keyword,
    };
    const res = await noticeListDB(notice) //스프링 플젝에서 요청하기 
    //-비동기상황연출-8000번서버-1521번경유
    //조회결과를 요청한다음에 초기화하기
    document.querySelector("#gubun").value ="분류선택";
    document.querySelector("#keyword").value="";
    console.log(res);
    setNotices(res.data);//여기서 usestate에 값이 채워진다
    console.log(notices);
  }

  const noticeSearch = (event) =>{
    console.log(`noticeSearch==>${event.key}`)
    if(event.key ==='Enter'){ //==r값만비교,===타입까지도 비교
      noticeList();
    }
  }

  return (
    <>
      <Header />
      <div className="container">
        <div className="page-header">
          <h2>
            공지관리 <small>글목록</small>
          </h2>
          <hr />
        </div>

        <div className="row">
          <div className="col-3">
            <select id="gubun" className="form-select" aria-label="분류선택">
              <option defaultValue>분류선택</option>
              <option value="n_title">제목</option>
              <option value="n_writer">작성자</option>
              <option value="n_content">내용</option>
            </select>
          </div>
          <div className="col-6">
            <input
              type="text"
              id="keyword"
              className="form-control"
              placeholder="검색어를 입력하세요"
              aria-label="검색어를 입력하세요"
              aria-describedby="btn_search"
              onKeyUp={noticeSearch}
            />
          </div>
          <div className="col-3">
            <Button variant="danger" id="btn_search" onClick={noticeList}>
              검색
            </Button>
          </div>
        </div>

        <div className="board-list">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>제목</th>
                <th>작성자</th>
              </tr>
            </thead>
            <tbody>
              {notices &&
                notices.map((notice, key) => (
                  // 리액트에서는 태그안에 속성값으로 현재 함수주소번지를 넘김수있다-props라고함
                  <NoticeRow key={key} notice={notice} />
                ))}
            </tbody>
          </Table>

          <div
            style={{
              margin: "10px",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              width: "100%",
            }}
          >
          </div>

          <hr />
          <div className="boardlist-footer">
            <Button variant="warning" onClick={noticeList}>
              전체조회
            </Button>
            &nbsp;
            <Button
              variant="success"
              onClick={() => {
                navigate(`/notice/write`);
              }}
            >
              글쓰기
            </Button>
          </div>
        </div>
      </div>
      <Bottom />
    </>
  )
}

export default NoticePage

/*
최초 실행페이지는 HomePage.jsx가 출력됨
상단(Header.jsx)메뉴바에서 공지사항을누르면 NoticePage함수 호출됨
이 때 useEffect훅에서 noticeListDB함수를 최초한번만 호출한다. 왜냐면 의존성배열이 빈깡통이라서

*/