import { useState } from 'react';
import './App.css';
import ChickenForm from './component/ChickenForm';
import ChickenList from './component/ChickenList';
import Modal from './component/Modal';

function MainRouter() {
  const [isModalOpen, setIsModalOpen] = useState(false);

  //const에서 동작하는 기능이 1개일 때 { } 생략 가능
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <div className="app-container">
      <h1>치킨가게 메뉴 관리</h1>
      <button className='chicken-register-button' onClick={openModal}>메뉴 등록하기</button>
      <ChickenList />
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm />
      </Modal>
    </div>
  );
}

export default MainRouter;
