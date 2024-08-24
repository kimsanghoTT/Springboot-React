import { useState } from 'react';
import './App.css';
import ChickenForm from './component/ChickenForm';
import ChickenList from './component/ChickenList';
import Modal from './component/Modal';
import { useNavigate } from 'react-router-dom';

function MainRouter() {
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 검색어 상태 추가 초기 검색값이 없기 때문에 공란
  const [searchTerm, setSearchTerm] = useState('');

  //const에서 동작하는 기능이 1개일 때 { } 생략 가능
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const navigate = useNavigate(); // 페이지 이동을 위한 hook
  const handle검색 = () => {
    navigate(`/search?query=${searchTerm}`); // 검색 페이지로 이동하면서 검색어 전달
  }

  return (
    <div className="app-container">
      <h1>치킨가게 메뉴 관리</h1>
      <div className="search-container">
        <input 
          type="text" 
          placeholder="검색하고 싶은 치킨 메뉴를 작성해주세요." 
          value={searchTerm} 
          onChange={(e) => setSearchTerm(e.target.value)} 
          className="search-input"
        />
        <button className="search-button" onClick={handle검색}>검색하기</button>
      </div>
      <button className='chicken-register-button' onClick={openModal}>메뉴 등록하기</button>
      <ChickenList />
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm />
      </Modal>
    </div>
  );
}

export default MainRouter;
