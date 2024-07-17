package todo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// VO = DB와 별개로 읽기 전용 ex) email인증번호
// DTO = DB에 값을 연동하는 것 ex) 회원 정보
// Entity = JPA DB Oracle에 테이블을 만들지 않아도 자동으로 테이블 생성, 컬럼 지정, 컬럼 값 설정

@Getter
@Setter
@ToString
public class Todo {

	private int TodoNo; 
	private String title;
	private String isDone; //할 일 완료 여부
	private int TodoMemberNo; //어떤 고객의 할 일인가 -> 고객 번호 연동
}
