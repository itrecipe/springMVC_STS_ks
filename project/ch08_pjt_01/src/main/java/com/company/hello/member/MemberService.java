package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao; //의존 객체(MemberDao) 자동 주입
	
	public int signUpConfirm(MemberVo memberVo) {
		System.out.println("[MemberService] signUpConfirm() 요청 성공!");
		System.out.println("------------------");
		System.out.println("m_id : " + memberVo.getM_id());
		System.out.println("m_pw : " + memberVo.getM_pw());
		System.out.println("m_mail : " + memberVo.getM_mail());
		System.out.println("m_phone : " + memberVo.getM_phone());

		memberDao.insertMember(memberVo); //memberVo를 매개변수로 전달
		
		return 0;
	}
	
	public MemberVo signInConfirm(MemberVo memberVo) {
		System.out.println("[MemberService] signInConfirm() 요청 성공!");
		
		MemberVo signInedMember = memberDao.selectMember(memberVo);
		
		return signInedMember;
	}
}
