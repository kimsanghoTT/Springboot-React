import React, { useState, useEffect } from "react";
import axios from 'axios';
import '../css/ChickenList.css'
import { Link, useNavigate } from "react-router-dom";

const ChickenList = () => {

    const navigate = useNavigate();
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

    /* navigate와 Link 사용에 있어 태그 or 기능을 사용하느냐 차이일 뿐임 */
    return (
        <div className='chicken-container'>
            <h1>치킨 메뉴</h1>
            <ul>
                {chickens.map(chicken => (
                    <li className="chicken-item" key={chicken.id}>
                        <div className="chicken-name">{chicken.chickenName}</div>
                        <div className="chicken-description">{chicken.description}</div>
                        <div className="chicken-price">₩{chicken.price.toLocaleString()}원</div>
                        <button className="detail-button"
                            onClick={
                                () => navigate(`/chicken-detail/${chicken.id}`)
                            } 
                            >상세보기</button>
                        {/* 
                        <Link to={`/chicken-detail/${chicken.id}`}>
                            <button className="detail-button" >상세보기</button>
                        </Link>
                        */}
                        
                    </li>
                ))}
            </ul>
        </div>

    )
}
export default ChickenList;