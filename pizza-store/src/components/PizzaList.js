import { useEffect, useState } from "react";
import axios from 'axios';
import '../css/PizzaList.css';

const PizzaList = () => {

    const [pizzas, setPizzas] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:9091/api/pizza")
        .then(res => {
            setPizzas(res.data);
        })
        .catch(err => {
            alert("데이터 불러오기 실패");
        })
    },[]);
    
    return(
        <div className="pizza-container">
            <h1>피자 메뉴</h1>
            <ul>
                {pizzas.map(pizza => (
                    <li key={pizza.name}>
                        <div className="pizza-name">{pizza.name}</div>
                        <div className="pizza-description">{pizza.description}</div>
                        <div className="pizza-price">{pizza.price}원</div>
                        <button>상세보기</button>
                    </li>
                ))}
            </ul>
        </div>
    )
    
}
export default PizzaList;