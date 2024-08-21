import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import '../css/ChickenDetail.css';

const ChickenDetail = () => {
    const {id} = useParams();
    console.log(id);
    
    const [chicken, setChicken] = useState(null);
    
    useEffect(() => {
        axios.get(`http://localhost:9090/api/chicken/${id}`)
        .then(res => {
            setChicken(res.data);
            console.log(res.data);
        })
    },[id])

    if(!chicken) {
        return(
            <div>로딩중...</div>
        )
    }
    return(
        <div className="chicken-detail-container">
            <h1>{chicken.chickenName}</h1>
            <p>{chicken.description}</p>
            <p>{chicken.price}원</p>
        </div>
    )
}
export default ChickenDetail;