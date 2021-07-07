package com.cot.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cot.blog.model.User;


// DAO
// 자동으로 been 등록
//@Repository // 생략이 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM user WHERE username =1?;
	Optional<User>findByUsername(String username);	

}


// JPA Naming 쿼리전략
	// select * from user where username =? and password =?;
	//User findByUsernameAndPassword(String username, String password);
	
	/*
	  @Query(value="select * from user where username =?1 and password =?2",
	  nativeQuery=true) User login(String username, String password);
	 */