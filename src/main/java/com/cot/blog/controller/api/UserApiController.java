package com.cot.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cot.blog.dto.ResponseDto;
import com.cot.blog.model.User;
import com.cot.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	// @Autowired private HttpSession session;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController: save 호출");
		// 실제로 DB에 insert를 하고 아래에서 return
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { // json데이터를 받기위해서는 @RequestBody 필요
		userService.회원수정(user);
		// 여기서 트랜젝션이 종료되기 때문에 DB값은 변경
		// 하지만 세션값은 변경이 안된 상태 >> 직접 세션값을 변경

		// 세션 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}

}

// 스프링 시큐리티 이용해서 로그인!
/*
 * @PostMapping("/api/user/login") public ResponseDto<Integer>
 * login(@RequestBody User user,HttpSession session) {
 * System.out.println("UserApiController: login호출"); User principal =
 * userService.로그인(user); //principal(접근주체)
 * 
 * if(principal!=null) { session.setAttribute("principal", principal); } return
 * new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
 */
