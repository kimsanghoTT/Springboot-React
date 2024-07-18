import React, {useState, useContext} from "react";
import TodoListContext from "./TodoListContext.js";

const TodoList = () => {

    const {todoList, setTodoList, loginMember} = useContext(TodoListContext);
    const [inputTodo, setInputTodo] = useState('');

    let keyIndex = 0;
    
    //할 일 추가 버튼 기능 설정
    const addTodo = () => {
        //할 일이 입력되지 않았다면 입력해달라는 알림창
        if(inputTodo.trim().length === 0){
            alert('할 일을 입력해주세요');
            return;
        }

        fetch("/todo", {//TodoController에서 /todo라는 url 주소에서 db에 값 추가
            method : "post",
            headers : {'Content-Type' : 'application/json'},
            body : JSON.stringify({
                title : inputTodo,
                todoMemberNo: loginMember.todoMemberNo
            })
        })
        .then(response => response.text())
        .then(todoNo => {
            if(Number(todoNo) === 0){
                return;
            }
            
            //기존 todoList + 새로 추가된 todo를 이용해 새 배열 생성, todoList에 대입            
            const newTodo = {
            todoNo : todoNo,
            title:inputTodo,
            isDone: 'Y',
            todoMemberNo: loginMember.todoMemberNo
           };

           //기존 todoList + newTodo를 이용해 새로운 할 일 만들기
           const newTodoList = [...todoList, newTodo];

           //todoList에 대입
           setTodoList(newTodoList);
           setInputTodo('');
        })

        //문제가 생기면 console창에서 보여주기
        .catch(
            e => console.log(e)
        )
    }

    //할 일을 완료하면 X버튼, 완료하지 못하면 O버튼 표시
    //특정 할 일의 번호를 받아 특정 할 일만 수정
    const updateTodo = (todo, index) => {
        console.log("todo : " + todo);
        console.log("index : " + index);

        fetch("/todo",{
            method: "put",

            //Content-Type = controller로 값을 전달할 때 어떤 파일(이미지, 동영상, 글자 등)인지 전달하는 공간
            headers:{'Content-Type' : 'application/json'},

            //JSON으로 된 파일을 문자로 변경, 글자 취급으로 사용
            body: JSON.stringify({
                todoNo: todo.todoNo,

                //할 일 완료 여부가 O로 되어있으면 X로 변경
                //X로 되어 있으면 O로 변경
                isDone: todo.isDone === 'Y' ? 'N' : 'Y' 

            })
        })
        .then(response => response.text())
        .then(result => {
            //응답에 대한 결과가 없으면 업데이트 실패했음을 알려주기
            if(result === '0'){
                alert('할 일 수정에 실패했습니다')
                return;
            }

            //수정 성공 시 todoList 값을 변경해서 새로고침

            //기존 할 일 목록(todoList)를 복사해서 새로 추가된 할 일을 더한 다음
            //해로운 할 일로 업데이트
            const newTodoList = [...todoList];
            
            //index 번호의 태그 값을 O or X로 변경
            newTodoList[index].isDone = newTodoList[index].isDone === 'Y' ? 'N' : 'Y';

            setTodoList(newTodoList);
        })
        .catch(e => console.log(e));
    }

    const deleteTodo = (todoNo, index) => {
        fetch("/todo", {
            method: 'delete',
            headers: {"Content-Type" : "application/json"},
            body: todoNo
        })
        .then(res => res.text())
        .then(result => {

            if(result === '0'){
                alert('삭제 실패');
                return;
            }
            const newTodoList = [...todoList];

            //배열.splice(인덱스, 숫자)
            //-> 배열의 인덱스 몇 번째 태그 부터 몇 칸을 잘라내서 반환할 지 지정
            //배열에서 잘라진 부분이 사라짐
            newTodoList.splice(index,1); //내가 선택한 번호, 하나만 삭제
            /*
            새로운 목록 리스트 = newTodoList
            괄호안에 작성한 부분 제외하고 목록 새로 작성 = splice
            (내가 선택한 번호, 하나만 삭제) = (index,1)
            */
            setTodoList(newTodoList); //새로 작성한 목록으로 기존 목록 대체
        })
        .catch(e => console.log(e));
    }


    return(
        <>
        <h1>{loginMember.name}의 Todo List</h1>
        <div className="todo-container">
            <h3>할 일(todo)입력</h3>
            <div>
                <input type="text" value={inputTodo} onChange={e => setInputTodo(e.target.value)}/>
                <button onClick={addTodo}>할 일 추가하기</button>
            </div>
            <ul>
                {/* 배열.map : 기존 배열 사용해서 새로운 배열 만들기 */}
                {todoList.map((todo, index) => (
                    <li key={keyIndex++}>
                        <div>
                            <span className={todo.isDone === 'N' ? 'todo-complete' : ''}>
                                {todo.title}
                            </span>
                            <span>
                                <button onClick={() => {updateTodo(todo, index)}}>
                                    {todo.isDone === 'Y' ? 'O' : 'X'}
                                </button>
                                <button onClick={() => deleteTodo(todo.todoNo, index)}>
                                    삭제
                                </button>
                            </span>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
        </>
    )
    
}
export default TodoList;