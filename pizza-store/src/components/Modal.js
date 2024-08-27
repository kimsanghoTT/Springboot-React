import '../css/PizzaRouter.css';

/* 
true, false를 나타낼 때 is를 주로 사용
이벤트(e)를 처리하기 위한 함수이름으로 쓸 때 on을 주로 사용.특정 사건이 발생했을 때 대처할 기능
    ex)닫기, 열기

*/

const Modal = ({ isOpen, onClose, children }) => {

    if (!isOpen) return null; //열기 전이라면 다시 돌려보내기

    return (
        <div className="modal-container">

            <button onClick={onClose}>&times;</button>
            {children}

        </div>
    )
}
export default Modal;