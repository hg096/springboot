package com.cot.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cot.blog.model.RoleType;
import com.cot.blog.model.User;
import com.cot.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록 IOC를 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 1234 원문
		String encpassword = encode.encode(rawPassword); // 해쉬 
		user.setPassword(encpassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}	


	/*
	 * @Transactional(readOnly=true) // select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */

/*
 * 서비스를 사용하는이유 - 트렌젝션 관리 - 여러개의 트렌젝션을 하나로 묶어서 사용
 */