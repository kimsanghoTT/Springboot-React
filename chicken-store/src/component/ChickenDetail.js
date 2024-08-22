import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import '../css/ChickenDetail.css';

const ChickenDetail = () => {
    const { id } = useParams();
    const [chicken, setChicken] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const navigate = useNavigate();
    //navigate : 기능 작성에서 주로 사용. const나 useEffect 안에 작성 -> 이동하는 동작에 소비자들의 의사가 반영이 되지 않음
    //Link : 태그에서 직접 주소 이동을 작성해 줄 때 사용

    //수정한 데이터 저장하는 공간
    const [editData, setEditData] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:9090/api/chicken/${id}`)
            .then(res => {
                setChicken(res.data);
                setEditData({
                    chickenName: res.data.chickenName,
                    description: res.data.description,
                    price: res.data.price
                });
                console.log(res.data);
            })

    }, [id])

    if (!chicken) {
        return (
            <div>로딩중...</div>
        )
    }

    const handleOpenForm = () => {
        setIsEditing(true);
    }

    const handleUpdate = () => {
        console.log(editData);

        axios.put(`http://localhost:9090/api/chicken/${id}`, editData, {
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(res => {
            setChicken(res.data); //기존 DB에 있는 내용 가져오기
            setEditData({
                chickenName: res.data.chickenName,
                description: res.data.description,
                price: res.data.price
            });
        })
        .catch(err => {
            console.log("에러 : ", err);
            
        })

        setIsEditing(false);

    }

    const deleteMenu = () => {
        axios.delete(`http://localhost:9090/api/chicken/${id}`)
        .then(res => {
            alert("삭제 완료");
            navigate("/");
        })
        .catch(err => {
            alert("삭제 실패");
        })
    }

    const 돌아가기 = () => {
        setIsEditing(false);
    }
    console.log(editData);
    
    return (
        <div className="chicken-detail-container">
            {/* 수정하기 버튼 누르면 수정하는 기능 나오고 안누르면 작성된 내용 보여주기 */}
            {
                isEditing ?
                    <div>
                        <input type="text" name="chickenName" value={editData.chickenName} onChange={(e) => setEditData({...editData, chickenName:e.target.value})} />
                        <textarea name="description" value={editData.description} onChange={(e) => setEditData({...editData, description:e.target.value})} />
                        <input type="number" name="price" value={editData.price} onChange={(e) => setEditData({...editData, price:e.target.value})} />
                        <button onClick={handleUpdate}>수정 완료</button>
                        <button onClick={돌아가기}>돌아가기</button>
                    </div>

                    :

                    <div>
                        <h1>{chicken.chickenName}</h1>
                        <p>{chicken.description}</p>
                        <p>{chicken.price}원</p>
                        <button onClick={handleOpenForm}>수정하기</button>
                        <button onClick={deleteMenu}>메뉴삭제</button>
                    </div>
            }
        </div>
    )
}
export default ChickenDetail;