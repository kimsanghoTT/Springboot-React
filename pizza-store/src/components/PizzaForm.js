import axios from "axios";
import { useState } from "react";
import '../css/PizzaForm.css';

const PizzaForm = () => {

    const [pizzaName, setPizzaName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');

    const submitData = {
        name : pizzaName,
        description : description,
        price : price
    }

    const handleRegister = () => {
        axios.post("http://localhost:9091/api/pizza", submitData)
        .then(res => {
            alert("등록 성공");
        })
        .catch(e => {
            alert("등록 실패");
        })
    }

    return (
        <div className="pizzaform-container">
            <label>
                메뉴 이름 : 
                <input type="text" value={pizzaName} onChange={(e) => setPizzaName(e.target.value)}/>
            </label>
            <label>
                메뉴 설명 : 
                <input type="text" value={description} onChange={(e) => setDescription(e.target.value)}/>
            </label>
            <label>
                메뉴 가격 : 
                <input type="number" value={price} onChange={(e) => setPrice(e.target.value)}/>
            </label>
            <button onClick={handleRegister}>등록하기</button>
        </div>
    )
}
export default PizzaForm;