import './App.css';
import {useState} from 'react';
import axios from 'axios';

function App() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = () => {
    //formData : 기본적으로 multipart 형식. -> 이미지를 쓴다는 전제 조건이 있음
    const formData = new FormData();
    
    formData.append('username', username);
    formData.append('password', password);
    formData.append('email', email);
    
    axios.post("http://localhost:9012/api/register", formData, {
      headers : {'Content-Type' : 'application/json'},
      params: {
        username : username,
        password : password,
        email : email
      }
    })
    .then(res => {
      alert("회원가입 완료");
    })
    
    /*
    const user = {
      username : username,
      email : email,
      password : password
    }
    
    fetch("http://localhost:9012/api/register", {
      method: "post",
      headers : {'Content-Type' : 'application/json'},
      //body : formData -> multipart로 보내게 됨
      body : JSON.stringify(user)
    })
    .then((res) => {
      res.text();
    })
    .then(data => {
      alert('회원가입 완료');
    })
    .catch(err => {
      alert('회원가입 실패');
    })
    */
    setUsername('');
    setPassword('');
    setEmail('');
  }
  return (
    <div className="App">
      <h2>회원가입</h2>
      <form>
        <label> 닉네임 : 
          <input value={username} onChange={(e) => setUsername(e.target.value)}/>
        </label>
        <label> 비밀번호 : 
          <input value={password} onChange={(e) => setPassword(e.target.value)}/>
        </label>
        <label> 이메일 : 
          <input value={email} onChange={(e) => setEmail(e.target.value)}/>
        </label>
        <button onClick={handleSubmit}>가입하기</button>
      </form>
    </div>
  );
}

export default App;
