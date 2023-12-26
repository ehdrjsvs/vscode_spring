import { Route, Routes } from 'react-router-dom';
import HomePage from './components/page/HomePage';
import BoardPage from './components/page/BoardPage';
import NoticePage from './components/page/NoticePage';
import NoticeWrite from './components/notice/NoticeWrite';
function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<HomePage />} />
        <Route path="/notice"  element={<NoticePage />} />
        <Route path="/notice/write"  element={<NoticeWrite />} />
        <Route path="/board"  element={<BoardPage />} />
      </Routes>
    </>
  );
}

export default App;
