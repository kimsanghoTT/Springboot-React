package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.dto.BCUser;
import com.kh.mapper.BCUserMapper;
import com.kh.repository.BCUserRepository;

@Service
public class BCUserService {
	

//	@Autowired
//	private BCUserRepository repository;

	@Autowired
	private BCUserMapper mapper;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	
	//패스워드 인코드를 저장
	public void saveUser(BCUser user) {
		user.setPassword(pwEncoder.encode(user.getPassword())); // 한 번 암호화된 암호를 가져옴
		mapper.saveUser(user);
		
		//jpaRepository 안에 save가 이미 저장되어 있기 때문에 굳이 메서드를 작성할 필요 없음
		//repository.save(user);	
	}
	
	

}
