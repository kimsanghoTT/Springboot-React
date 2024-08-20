import React, {useState} from 'react';
import axios from 'axios';
import '../css/ChickenForm.css';

const ChickenForm = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');

    console.log(name);
    console.log(description);
    console.log(price);

    const submitData = {
        chickenName : name,
        description : description,
        price : price
    }
    
    const submitBtn = () => {
        axios.post("http://localhost:9090/api/chicken", submitData)
        .then(res => {
            alert("값 들어감");
        })
        .catch(err => {
            alert("안들어감");
        })
    }
    return (
        <div className="chickenForm-container">
            <label>
                메뉴 이름 :
                <input value={name} onChange={(e) => setName(e.target.value)}/>
            </label>
            <label>
                메뉴 설명 : 
                <textarea value={description} onChange={(e) => setDescription(e.target.value)}/>
            </label>
            <label>
                가격 : 
                <input type="number" value={price} onChange={(e) => setPrice(e.target.value)}/>
                <button onClick={submitBtn}>등록하기</button>
                <button>메인으로 돌아가기</button>
            </label>
        </div>
    )
}
export default ChickenForm;