package todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.dto.Todo;
import todo.dto.TodoMember;
import todo.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService service;
	
	//아이디 중복 검사
	//중복시 1, 아닐 시 0 반환
	//select count 했을 때 존재하면 1로 넘어오고 없으면 0으로 넘어옴
	@GetMapping("/idCheck")
	public int idCheck(@RequestParam("id") String id) {
		return service.idCheck(id);
	}
	
	//회원 가입
	//성공시 1, 실패시 0 반환
	@PostMapping("/signup")
	public int signup(@RequestBody TodoMember member) {
		return service.signup(member);
	}
	
	//로그인
	//성공시 회원 정보 /todoList, 실패 null
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody TodoMember member){
		return service.login(member);
	}
	
	//할 일 추가
	//@Param todo
	//return 성공시 1 : 실패시 0
	@PostMapping("/todo")
	public int insert(@RequestBody Todo todo) {
		return service.insert(todo);
	}
	
	//할 일 수정
	//Param todo
	//return 성공시 1 : 실패시 0
	//update 수정 -> @PutMapping
	//PostMapping을 써도 됨
	@PutMapping("/todo")
	public int update(@RequestBody Todo todo) {
		return service.update(todo);
	}
	
	//할 일 삭제
	//Param todoNo
	//return 성공시 1 : 실패시 0
	//delete 삭제 -> @DeleteMapping
	//PostMapping을 써도 됨
	@DeleteMapping("/todo")
	public int delete(@RequestBody int todoNo) {
		return service.delete(todoNo);
	}
	
	@GetMapping("/test")
	public int test() {
		return 100;
	}
	
	/*
	CRUD : DataBase에서 데이터 조작의 기본적인 네가지 작업
		Create : 새로운 데이터를 생성 -> INSERT
		Read : 데이터 조회 -> SELECT
		Update : 데이터 수정 -> UPDATE
		Delete : 데이터 삭제 -> DELETE
		
		insert, select, update, delete : DML
		alter, create, drop : DDL
		grant, revoke : DCL
		
		get post put deleted : http메서드(웹주소에서 사용되는 기능 명칭)
		HTTP메서드 : http(웹)에서 사용자가 서버에 회원가입이나 로그인 같은 요청을 보낼 때 사용하는 기능, 명칭
		
		http 메서드는 CRUD 작업과 연결되어 사용
				
			GET : 서버로부터 데이터를 조회하기 위한 요청 -> CRUD의 R
				ex) get /user = 모든 사용자 목록을 조회
				
			POST : 클라이언트가 서버에 새로운 데이터를 생성하기 위한 요청 -> CRUDE의 C
				ex) post /user = 새로운 사용자를 생성. body에 사용자의 정보가 포함되어 db에 전송
				
			PUT : 클라이언트가 서버에 존재하는 데이터를 본인의 의도대로 업데이트를 해달라고 요청 -> CRUD의 U
				ex) put /mypage = 사용자가 기존의 자신의 정보를 수정해달라고 서버에 요청, db가 수정
				
			DELETE : 클라이언트가 서버에 있는 데이터를 삭제하기 위한 요청 -> CRUD의 D
				ex) delete /user/1 = 회원번호가 1인 사용자를 삭제
	*/
}