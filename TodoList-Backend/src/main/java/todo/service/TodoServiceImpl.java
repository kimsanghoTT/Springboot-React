package todo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.dto.Todo;
import todo.dto.TodoMember;
import todo.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoMapper mapper;
	
	@Override
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}
	
	@Override
	public int signup(TodoMember member) {
		return mapper.signup(member);
	}
	
	@Override
	public Map<String, Object> login(TodoMember member){
		TodoMember loginMember = mapper.login(member);
		Map<String, Object> map = new HashMap<>();
		
		map.put("loginMember", loginMember);
		
		//로그인한 멤버 정보가 조회되면 그 멤버가 작성한 todoList를 보여줌
		if(loginMember != null) {
			List<Todo> todoList = mapper.selectTodoList(loginMember.getTodoMemberNo());
			
			//Map 형태로 프론트엔드에 전달. todoList라는 이름(key)으로 todoList 내용(value)전달
			map.put("todoList", todoList);
		}
		return map;
	}
	
	@Override
	public int insert(Todo todo) {
		int result = mapper.insert(todo);
		
		//결과가 참이면 todo.getTodoNo()->할 일 번호 가져와서 넣어줌
		//거짓일 경우 0을 전달
		return result > 0 ? todo.getTodoNo() : 0;
	}
	@Override
	public int update(Todo todo) {
		return mapper.update(todo);
	}
	@Override
	public int delete(int TodoNo) {
		return mapper.delete(TodoNo);
	}
	
	//react에서 map(key: value) 형태로 이름(key)과 키에 해당하는 값(value)이 들어옴
}
