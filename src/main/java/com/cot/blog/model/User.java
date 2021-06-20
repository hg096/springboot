package com.cot.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더패턴!
//ORM > Java(언어) Object > 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블 생성
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링을 따라감
	private int id; // 시퀀스, 자동증가
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 암호화 예정
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'") 
	private String role; // Enum을 추천 // admin, user, manager
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
