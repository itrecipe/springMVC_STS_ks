package com.exam.board.mapper;

import com.exam.board.domain.AuthVO;
import com.exam.board.domain.MemberVO;

public interface MemberMapper {

	public MemberVO read(String userid);

	public int memberJoin(MemberVO mVO);

	public int memberAuth(AuthVO aVO);

}
