package com.kh.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Profile;
import com.kh.mapper.ProfileMapper;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Value("${file.upload-dir}")
	private String profileDir;
	
	@Autowired
	private ProfileMapper mapper;
	
	@Override
	public List<Profile> getProfile() {
		return mapper.getProfile();
	}
	
	@Override
	public void insertProfile(Profile profile) {
		mapper.insertProfile(profile);
	}
	
	@Override
	public void uploadProfile(MultipartFile[] files, String username) {
		File profileDirFile = new File(profileDir);
		
		//폴더도 하나의 파일이므로 파일로 폴더 여부 확인
		if(!profileDirFile.exists()) {
			
			if(!profileDirFile.mkdirs()) {
				System.out.println("폴더 생성");
				throw new RuntimeException("폴더 생성 실패");
			}
		}
		//프로필을 업데이트 하기 위해 받은 이미지가 없으므로 처음 이미지들 이름은 null로 설정
		List<String> fileNames = null;
		try {
			
			fileNames = List.of(files).stream().map(file -> {
				String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				File df = new File(profileDir + File.separator + fileName);
				
				try {
					file.transferTo(df);
				}
				catch (Exception e) {
					throw new RuntimeException("파일 업로드 실패", e);
				}
				
				return fileName;
			}).collect(Collectors.toList());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Profile profile = new Profile();
		profile.setUsername(username);
		profile.setProfileImageUrl(String.join(",", fileNames));
		insertProfile(profile); //set으로 추가된 값을 DB에 넣어주기
		
	}
}
