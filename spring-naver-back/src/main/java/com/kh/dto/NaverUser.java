package com.kh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NaverUser {

	//id ~ 프로필 사진 까지는 네이버에 저장된 값을 가져와서 DB에 저장
	private String id;
	private String email;
	private String nickname;
	private String name;
	private String gender;
	private String profileImage;
	
	//비밀번호는 사용자가 작성한 비밀번호를 가져와서 DB에 저장
	private String password;
}
