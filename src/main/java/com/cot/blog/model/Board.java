package com.cot.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러이 <html>태그가 섞여 디자인


	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER) // Many=Board, User=one
	@JoinColumn(name = "userId") // userId는 만들 칼럼이름, PK키를 따라감
	private User user; // DB는 오브젝트 저장불가, FK,자바는 오브젝트를 저장가능

	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE ) // mappedBy 연관관계의 주인이 아니다(FK가 아님) DB에 컬럼X
	@JsonIgnoreProperties({"board"}) // 무한 참조 방지
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}
