package com.cot.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링을 따라감
	private int id; // 시퀀스, 자동증가
	
	@Column(nullable=false, length= 200)
	private String content; // 섬머노트 라이브러이 <html>태그가 섞여 디자인
	
	@ManyToOne
	@JoinColumn(name="boardId") // boardId는 만들 칼럼이름, PK키를 따라감
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId") // userId는 만들 칼럼이름, PK키를 따라감
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
