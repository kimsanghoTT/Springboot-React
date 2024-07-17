import React, {useState} from "react";
import LoginContext from "./component/LoginContext.js";
import SignUp from './component/SignUp.js';
import Login from './component/Login.js';

function App() {
  const [loginMember, setLoginMember] = useState(null);
  const [signUpView, setSignUpView] = useState(false);
  return (
    <LoginContext.Provider value={{loginMember, setLoginMember}}>
      <button onClick={() => {setSignUpView(!signUpView)}}>
        {signUpView ? ('회원가입 닫기') : ('회원가입 열기')}
      </button>
      <div className='"signup-wrapper'>
        {signUpView === true && (<SignUp/>)}
      </div>  
      <h1>Todo List</h1>
      <Login/>
      {/* {loginMember !== null && (<TodoList/>)}*/}
    </LoginContext.Provider>
  );
}

export default App;
