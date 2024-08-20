import '../css/Modal.css';

//버튼을 열거나 닫을 때 동작
const Modal = ({isOpen, onClose, children}) => {

    //넘어온 isOpen이 false면 UI가 담긴 return을 보지 않고 컴포넌트 종료 -> 다시 돌려보냄
    if(!isOpen) {
        return null;
    }

    return(
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="modal-close" onClick={onClose}>
                    &times;
                </button>
                {children}
            </div>
        </div>
    )
}
export default Modal;