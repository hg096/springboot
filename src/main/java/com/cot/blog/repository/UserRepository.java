package com.cot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cot.blog.model.User;


// DAO
// 자동으로 been 등록
//@Repository // 생략이 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
