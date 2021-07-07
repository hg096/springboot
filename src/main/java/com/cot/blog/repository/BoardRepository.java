package com.cot.blog.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.cot.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {

	
}

