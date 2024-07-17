package todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import todo.dto.Todo;
import todo.dto.TodoMember;

@Mapper
public interface TodoMapper {
	
	int idCheck(String id);
	int signup(TodoMember member);
	TodoMember login(TodoMember member);
	List<Todo> selectTodoList(int TodoMemberNo);
	int insert(Todo member);
	int update(Todo member);
	int delete(int TodoNo);
}
