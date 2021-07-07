package com.cot.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cot.blog.model.User;
import com.cot.blog.repository.UserRepository;


@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	// 스프링이 로그인 요청을 가로챌때. username, password 변수 두개를 가로챔 
	// password 부분 처리는 알아서
	// username이  DB에 있는지만 확인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다: "+username);
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저정보 저장 
	}
}
