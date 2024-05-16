package com.exam.board.service;

import java.util.List;

import com.exam.board.domain.Criteria;
import com.exam.board.domain.ReplyPageDTO;
import com.exam.board.domain.ReplyVO;

public interface ReplyService {
	
	//mapper의 insert
	public int register(ReplyVO vo);  
    
	//mapper의 read
	public ReplyVO get(Long rno);
	
	//mapper의 update
	public int modify(ReplyVO vo);
	
	//mapper의 delete
	public int remove(Long rno);

    //mapper의 getListWithPaging
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
