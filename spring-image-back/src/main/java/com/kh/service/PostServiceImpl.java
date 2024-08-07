package com.kh.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper mapper;
	
	@Value("${file.upload-dir}")//application.properties에서 설정 가져와서 사용
	private String uploadDir; // = C:/Users/user1/Desktop/saveImage
	
	@Override
	public List<Post> findAll(){
		return mapper.findAll();
	}
	
	@Override
	public void insertPost(Post post) {
		mapper.insertPost(post);
	}
	
	@Override
	public void uploadImages(MultipartFile[] files, String title, String content) {
		//바탕화면에 이미지를 저장하고 불러올 폴더가 존재하는지 확인
		File uploadDirFile = new File(uploadDir);
		
		// 만약에 폴더가 존재하지 않을 경우
		if(!uploadDirFile.exists()) {
			System.out.println("폴더가 없으므로 폴더 새로 생성");
			
			//mkdirs = 하위 폴더 모두 생성
			if(!uploadDirFile.mkdirs()) {
				throw new RuntimeException("폴더 생성 실패");
			}
			
		}
		//이미지 이름 중복없이 저장할 수 있도록 설정
		List<String> fileNames = null; //파일명이 여러개일 수 있기 때문에 fileNames = 파일이름들 리스트로 글자목록을 작성
		
		try {
			fileNames = List.of(files).stream().map(file -> {
				//파일을 저장 폴더에 저장할 때 이미지에 랜덤으로 이름을 부여해서 저장
				//UUID : 파일 이름이 겹치지 않도록 랜덤으로 이름 생성
				
				String fileName1 = UUID.randomUUID().toString();
				
				//랜덤으로 작성한 이름 뒤에 원본 이름을 붙이고 싶을 때 -> getOriginalFilename()
				String fileName2 = UUID.randomUUID().toString() + file.getOriginalFilename();
				
				//랜덤으로 작성한 이름과 원본 이름을 _로 구분짓고 싶을 때
				String fileName3 = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				
				//폴더 안에 이미지들 저장하기
				File df = new File(uploadDir + File.separator + fileName3);
				//File.separator : 파일 경로 구분. window(\, /)랑 맥북(/) 모두 경로를 알아서 잡아줌
				
				try {
					file.transferTo(df);
				}
				catch (Exception e) {
					throw new RuntimeException("파일 업로드 실패", e);
				}
				
				return fileName3;
			}).collect(Collectors.toList());
			/*
			MultipartFile[] files : array 배열로 파일들 담기
			List.of(files) : 파일들 배열을 리스트(목록)로 변환
			stream() : 리스트나 배열같은 데이터를 하나씩 처리하는 기능
			collect(Collectors.toList()) : stream으로 가져온 이미지 데이터를 리스트로 정렬
			
			이미지들(files) 에서 이미지를 한 개씩 담아오기(map) -> 이미지를 하나씩 가져옴(stream)
			-> stream을 이용해서 가져온 이미지를 리스트로 모음(collect) -> 한번 더 리스트로 목록 변환(toList())
			*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//이미지 이름, 경로 설정울 바탕으로 DB에 넣어주기
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setImageUrl(String.join(",", fileNames));
		insertPost(post);
	}
	
}
