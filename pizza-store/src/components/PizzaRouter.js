import { useState } from "react";
import PizzaForm from "./PizzaForm";
import PizzaList from "./PizzaList";
import Modal from "./Modal";
import '../css/PizzaRouter.css';
import { useNavigate } from "react-router-dom";

const PizzaRouter = () => {
    //모달 관련 변수, 기능
    const [isModalOpen, setIsModalOpen] = useState(false);
    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    //검색 관련 변수, 기능
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();
    const handleSearch = () => {
        navigate(`/search?query=${searchTerm}`);
    }

    return (
        <div className="app-container">
            <h1>피자 메뉴 검색하기</h1>
            <div className="search-container">
                <input type="text" placeholder="검색하고 싶은 치킨 메뉴를 작성해주세요"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                />
                <button onClick={handleSearch}>검색하기</button>
            </div>
            <PizzaList />
            <button onClick={openModal}>메뉴 등록하기</button>
            <Modal isOpen={isModalOpen} onClose={closeModal}>
                <PizzaForm />
            </Modal>
        </div>
    )
}
export default PizzaRouter;