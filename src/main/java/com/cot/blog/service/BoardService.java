package com.cot.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cot.blog.dto.ReplySaveRequestDto;
import com.cot.blog.model.Board;
import com.cot.blog.model.User;
import com.cot.blog.repository.BoardRepository;
import com.cot.blog.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록 IOC를 해줌
@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository; // 아래 주석과 같은 역할
	/* @Autowired private BoardRepository boardRepository; 
	  @Autowired private ReplyRepository replyRepository; */
	

	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없음");
		});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패:아이디를 찾을 수 없음");
		}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시에 트랜젝션이 Service가 종료될때 트랜잭션 종료 >> 더티체킹 >> 자동 업데이트 DB flush
	}

	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(),
				replySaveRequestDto.getContent());

		/*
		 * User user =
		 * userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
		 * return new IllegalArgumentException("댓글쓰기 실패: 유저 Id를 찾을 수 없음"); }); // 영속화 완료
		 * Board board =
		 * boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() ->
		 * { return new IllegalArgumentException("댓글쓰기 실패: 게시글 Id를 찾을 수 없음"); }); // 영속화
		 * 완료
		 * 
		 * Reply reply = new Reply(); reply.update(user, board,
		 * replySaveRequestDto.getContent());
		 * 
		 * 
		 * replyRepository.save(reply);
		 */
	}
	//댓글 삭제
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

}

/*
 * 서비스를 사용하는이유 - 트렌젝션 관리 - 여러개의 트렌젝션을 하나로 묶어서 사용
 */