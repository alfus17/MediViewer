package com.study.medi.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvContoller {
	
	@Value("${java.file.test}") // 변수 파일에 등록된 java.file.test 값 가져오기
	String envValue;
	
	@Value("${dicome.img.path}") // 변수 파일에 등록된 이미지 폴더 경로
	String envImgPath;
	
	@GetMapping("/getEnvValue")
	public ResponseEntity<Object> getEnvValue() {
		return new ResponseEntity<Object>(envValue, HttpStatus.OK);
	}
	
	@GetMapping("/getImgPath")
	public ResponseEntity<Object> getEnvImgPath() {
		return new ResponseEntity<Object>(envImgPath, HttpStatus.OK);
	}
}