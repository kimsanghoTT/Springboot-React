import React, {useState, useContext} from "react";
import LoginContext from "./LoginContext";

const Login = () => {
    const {loginMember, setLoginMember} = useContext(LoginContext);

    const [id, setId] = useState('');
    const [pw, setPw] = useState('');

    const loginBtn = () => {
        fetch("/login",{
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
                "Accept" : "application/json"
            },
            body: JSON.stringify({id : id, pw : pw})
        })
        .then(response => response.json())
        .then(map => {
            console.log(map);

            //로그인 실패시
            if(map.loginMember === null){
                alert('아이디 또는 비밀번호가 잘못되었습니다.');
                return;
            }

            //로그인 성공시
            setLoginMember(map.loginMember);

            setId('');
            setPw('');
            alert("로그인 성공");
        })
    }

    const logout = () => {
        setLoginMember(null);
    }

    return(
        <div className="login-container">
            <table>
                <tbody>
                    <tr>
                        <th>ID</th>
                        <td>
                            <input type="text" onChange={e => setId(e.target.value)} value={id}/>
                        </td>
                    </tr>
                    <tr>
                        <th>PW</th>
                        <td>
                            <input type="password" onChange={e => setPw(e.target.value)} value={pw}/>
                        </td>
                        <td>
                            <button onClick={loginBtn}>로그인</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            {loginMember && (
                <button onClick={logout}>로그아웃</button>
            )}
        </div>
    )
}
export default Login;