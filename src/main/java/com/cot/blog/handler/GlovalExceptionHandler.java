package com.cot.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cot.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlovalExceptionHandler {

	@ExceptionHandler(value=Exception.class) // 모든 예외를 여기서 처리 
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
		
	}
}

// get요청 ?key=value body로 데이터를 담아 보내지 않음
// post, put, delete 요청(데이터 변경)
// 데이터를 담아보내야할게 많음 -form태그(get, post만 가능)
// 자바스크립트로 요청

// 통일 : 자바스트립트로 ajax요청 + 데이터는 json으로 통일
// form:form 태그 >> post, put, delete, get 가능