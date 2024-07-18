import React, {useState} from "react";

const SignUp = () => {
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [name, setName] = useState('');
    const [result, setResult] = useState('');

    //아이디 중복검사
    const [idValidation, setIdValidation] = useState(false);

    //중복검사 이벤트 핸들러
    const idDup = (eventId) => {
        setId(eventId);

        //입력된 아이디가 4글자 미만일 경우
        if(eventId.trim().length < 4){
            setIdValidation(false);
            return;
        }

        fetch("/idCheck?id=" + eventId)
        .then(response => response.text())
        .then(result => {
            if(Number(result) === 0){
                setIdValidation(true);
            }
            else{
                setIdValidation(false);
            }
        })
    }

    const signUpBtn = () => {
        if(!idValidation){
            alert('유효한 아이디가 아닙니다.');
            return;
        }

        if(pw !== pwCheck){
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }

        const inputValues = {};
        inputValues.id = id;
        inputValues.pw = pw;
        inputValues.name = name;

        fetch("/signup", {
            method: "POST",
            headers: {"Content-Type" : "application/json"},
            body: JSON.stringify(inputValues)
            
        })
        .then(response => response.text())
        .then(result => {
            if(Number(result) > 0){
                setResult('회원 가입 성공');
                setId('');
                setPw('');
                setPwCheck('');
                setName('');
            }
            else{
                setResult('회원 가입 실패');
            }
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
    }

    return(
        <div className="signup-container">
            {/* label의 htmlFor와 input의 id를 생략하고 싶다면 input을 라벨 안에 작성해주면 됨 */}
            <label>
                ID : 
                <input type="text" onChange={e => idDup(e.target.value)}
                value={id}
                className={idValidation ? '' : 'id-err'}
                />
            </label>
            
            <label>
                PW : 
                <input type="password" onChange={e => setPw(e.target.value)}
                value={pw}
                />
            </label>
            <label>
                PW CHECK : 
                <input type="password" onChange={e => setPwCheck(e.target.value)}
                value={pwCheck}
                />
            </label>
            <label>
                NAME : 
                <input type="text" onChange={e => setName(e.target.value)}
                value={name}
                />
            </label>
            
            <button onClick={signUpBtn}>가입하기</button>
            <hr/>

            {/* 회원가입 결과 출력 */}
            <h3>{result}</h3>
        </div>
    )

}
export default SignUp;