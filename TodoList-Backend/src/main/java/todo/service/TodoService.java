package todo.service;

import java.util.Map;

import todo.dto.Todo;
import todo.dto.TodoMember;

public interface TodoService {

	int idCheck(String id);
	int signup(TodoMember member);
	Map<String, Object> login(TodoMember member);
	int insert(Todo member);
	int update(Todo member);
	int delete(int TodoNo);
}
