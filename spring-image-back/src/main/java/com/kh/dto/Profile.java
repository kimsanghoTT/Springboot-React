package com.kh.dto;

import java.time.LocalDateTime;

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
public class Profile {

	private int userId;
	private String username;
	private String profileImageUrl;
	private LocalDateTime createdAt; //LocalDateTime = 현재 날짜, 시간
}
