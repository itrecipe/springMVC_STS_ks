package com.company.hello.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	
	private Map<String, MemberVo> memberDB = new HashMap<String, MemberVo>();
	
	public void insertMember(MemberVo memberVo) {
		System.out.println("[MemberDao] insertMember() 요청 성공!");
		System.out.println("m_id : " + memberVo.getM_id());
		System.out.println("m_pw : " + memberVo.getM_pw());
		System.out.println("m_mail : " + memberVo.getM_mail());
		System.out.println("m_phone : " + memberVo.getM_phone());

		memberDB.put(memberVo.getM_id(), memberVo); //새로운 회원 정보 추가
		printAllMember(); //모든 회원 정보 추가
	}
	
	public void printAllMember() {
		System.out.println("[MemberDao] printAllMember() 요청 성공!");
		
		Set<String> keys = memberDB.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			MemberVo memberVo = memberDB.get(key);
			
			System.out.println("m_id : " + memberVo.getM_id());
			System.out.println("m_pw : " + memberVo.getM_pw());
			System.out.println("m_mail : " + memberVo.getM_mail());
			System.out.println("m_phone : " + memberVo.getM_phone());
		}
	}
}