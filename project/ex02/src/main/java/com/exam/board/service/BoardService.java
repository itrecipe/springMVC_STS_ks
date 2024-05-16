package com.exam.board.service;

import java.util.List;

import com.exam.board.domain.BoardAttachVO;
import com.exam.board.domain.BoardVO;
import com.exam.board.domain.Criteria;

//비지니스 계층의 인터페이스
public interface BoardService {
	
	public void register(BoardVO board); //Create

	public BoardVO get(Long bno); //Read

	public boolean modify(BoardVO board); //Update

	public boolean remove(Long bno); //delete

	//public List<BoardVO> getList(); //목록 select
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public List<BoardAttachVO> getAttachList(Long bno);

}
