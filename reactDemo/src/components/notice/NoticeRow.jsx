import React from 'react'
import { Link } from 'react-router-dom'
//전체조회결과에서 한건씩처리할 화면을그려줄 함수를 따로 선언하였다
//함수를 태그이름으로 사용할 수 있다. -props를 통해서 현재 페이지주소를 하위페이지에 넘길수있음.
const NoticeRow = ({notice}) => {
  console.log(notice);
  console.log(notice.N_TITLE);
  return (
    <>
      <tr>
        <td>{notice.N_NO}</td>
        <td>
          <Link to={"/noticedetail/"+notice.N_NO} className='btn btn-primary'>{notice.N_TITLE}</Link>
        </td>
        <td>{notice.N_WRITER}</td>
      </tr>  
    </>
  )
}

export default NoticeRow