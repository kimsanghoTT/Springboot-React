import React, { useState, useEffect } from "react";
import axios from 'axios';

const ChickenList = () => {

    const [chickens, setChickens] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:9090/api/chicken")
        .then(res => {
            setChickens(res.data); //가져온 데이터를 chickens 변수에 저장
        })
        .catch(err => {
            alert("오류 발생");
        })
    }, [])

    const deleteMenu = (index) => {
        setChickens(chickens.filter(chicken => index !== chicken.id));
    }

    return(
        <div className='chicken-container'>
            <h1>치킨 메뉴</h1>
            <ul>
                {chickens.map(chicken => (
                    <li key={chicken.id}>
                        {chicken.chickenName} = {chicken.description} = ₩{chicken.price}
                        <button onClick={() => deleteMenu(chicken.id)}>메뉴삭제</button>
                    </li>
                ))}
                
            </ul>
        </div>
        
    )
}
export default ChickenList;