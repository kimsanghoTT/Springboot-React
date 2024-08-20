import { useState } from 'react';
import './App.css';
import ChickenForm from './component/ChickenForm';
import ChickenList from './component/ChickenList';
import Modal from './component/Modal';

function App() {
  const [isModalOpen, setIsModalOpen] = useState(false);

  //const에서 동작하는 기능이 1개일 때 { } 생략 가능
  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <div className="app-container">
      <ChickenList />
      <button onClick={openModal}>메뉴 등록하기</button>
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm />
      </Modal>
    </div>
  );
}

export default App;
