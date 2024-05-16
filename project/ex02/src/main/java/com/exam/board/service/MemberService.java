package com.exam.board.service;

import com.exam.board.domain.MemberVO;

public interface MemberService {
	
	public String joinIdCheck(String userid);
	
	public int joinRegister(MemberVO vo);

}
