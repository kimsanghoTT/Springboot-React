import { useEffect, useState } from 'react';
import axios from 'axios';

const AddressSearching = () => {
    const [address, setAddress] = useState('');
    const [additionalAddress, setAdditionalAddress] = useState('');
    const [finalAddress, setFinalAddress] = useState('');

    //백엔드 api url 주소를 /api/addUser로 Restful 연결
    //Restful 연결 = 클라이언트와 DB를 연결해서 DB에 값을 넣음

    //1.fetch
    const submitToDB = () => {
        fetch("http://localhost:8080/api/addUser", {
            method: 'POST',
            headers:{'Content-Type' : 'application/json'},
            // java에서 파라미터명도 address로 해줘야함
            body: JSON.stringify({address:finalAddress})
        })
        .then(res => {
            res.json();
            alert("등록성공");
        })
        .catch(err => {
            alert("등록실패");
        })
        
    }

    //2.axios
    const submitToDB2 = () => {
        axios.post("http://localhost:8080/api/addUser", JSON.stringify({address:finalAddress}))
        .then(res => {
            alert('등록성공');
        })
        .catch(err => {
            alert('등록실패');
        })
    }

    //주소 검색을 완료하고 사용자가 검색한 데이터를 가져와서 기능 실행하기
    const handleComplete = (data) => {

        //사용자가 선택한 기본 주소를 저장
        let fullAddress = data.address; //서울 강남구 강남대로 298 (역삼동, KB라이프타워)

        //상세 주소 입력
        let extraAddress = ''; // OO동 OOOO호 

        if (data.addressType === 'R') {// R : 도로명 주소 , J : 지번 주소

            // bname : 특정 동이 존재하면 추가
            if (data.bname !== '') {
                extraAddress += data.bname;
            }

            // 특정 빌딩 이름이 존재하면 추가
            if (data.buildingName !== '') {
                extraAddress +=
                    extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
            }

            //모든 주소 합쳐서 정리
            fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
        }

        //완성된 주소
        setAddress(fullAddress);
    };

    const openPostcode = () => {
        //주소 검색 새 창 열기
        //new window.daum : new.window(새 창) + .daum(다음에서 제공하는 서비스 실행)
        new window.daum.Postcode({
            //oncomplete : 사용자가 주소 검색을 완료했을 때 호출할 함수(실행할 기능) 지정
            //oncomplete : 다음에서 제공, handleComplete : 개발자가 만든 기능
            oncomplete: handleComplete,
        }).open();
    };

    useEffect(() => {
        setFinalAddress(`${address} ${additionalAddress}`);
    },[address, additionalAddress])

    return (
        <div>
            <button onClick={openPostcode}>주소 검색</button>
            {address && (
                <div>
                    <input placeholder='상세 주소 입력'
                        value={additionalAddress}
                        onChange={(e) => setAdditionalAddress(e.target.value)} />

                    <div>선택한 주소: {address}</div>
                </div>)}

            {address && additionalAddress && (
                <>
                    <p>최종 추가 주소</p>
                    <h5>{finalAddress}</h5>

                    {/* 나중에 value 값으로 최종 주소를 DB에 넣어야 할 때 사용 
                    -> hidden으로 처리해서 소비자 눈에 보이지 않게 함 */}
                    <input value={finalAddress} disabled/>
                </>
            )}
            <button onClick={submitToDB}>제출하기</button>
        </div>

    )
}
export default AddressSearching;