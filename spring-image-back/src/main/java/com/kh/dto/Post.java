package com.kh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

	private int id;
	private String title;
	private String content;
	private String imageUrl;
	//id와 createAt은 mysql에서 자동으로 생성해주므로 mapper.xml에 작성 x
	private String createdAt; //게시판에 작성한 글과 이미지가 mysql에 작성한 시간
}
