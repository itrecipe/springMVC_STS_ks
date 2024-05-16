package com.company.hello.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	//멤버 서비스 객체 생성(의존 객체 직접 생성) - 자동 주입 전
	//MemberService memberService = new MemberService();
	
	//의존 객체 자동 주입 - 자동 주입 후
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/signUp")
	public String signUp() {
		System.out.println("sign_up.jsp 요청 성공!");
		return "sign_up";
	}

	@RequestMapping("/signIn")
	public String signIn() {
		System.out.println("sign_in.jsp 요청 성공!");
		return "sign_in";
	}
	
	/* @RequestParam을 이용하면
	 * 사용자가 입력하는 값을 하나씩 받을 수 있다
	 * (단, 받을 데이터가 몇개 안될때는 유용하나 많아지면 
	 * 코드가 길어져서 효율이 좋진 않다.
	 */

	/* vo 사용전 @RequestParam을 이용하는 방법
	public String signUpConfirm(@RequestParam String m_id, 
			//@RequestParam String m_pw //String 문자열로 비번을 받음
			//@RequestParam Integer m_pw, //Integer형으로 비번을 받음
			@RequestParam int m_pw,
			@RequestParam String m_mail, 
			@RequestParam String m_phone) {

		System.out.println("[MemberController] signUpConfirm() 요청 성공!");
		System.out.println("------------------");
		System.out.println("m_id : " + m_id);
		System.out.println("m_pw : " + m_pw);
		//System.out.println("m_pw_type : " + m_pw.getClass().getName()); //Integer형으로 비번을 받는 방법 
		System.out.println("m_pw_type : " + ((Object) m_pw).getClass().getName()); //기본형 int로도 비번을 받을 수 있음
		System.out.println("m_mail : " + m_mail);
		System.out.println("m_phone : " + m_phone);

		return null;
	}
	*/
	
	//vo 객체를 사용하는 방법
	@RequestMapping("/signUpConfirm")
	public String signConfirm(MemberVo memberVo) {
		System.out.println("[MemberController] signUpConfirm() 요청 성공!");
		System.out.println("------------------");
		System.out.println("m_id : " + memberVo.getM_id());
		System.out.println("m_pw : " + memberVo.getM_pw());
		System.out.println("m_mail : " + memberVo.getM_mail());
		System.out.println("m_phone : " + memberVo.getM_phone());
		
		memberService.signUpConfirm(memberVo);

		return "sign_up_ok";
	}
	
	@RequestMapping("/signInConfirm")
	public String signInConfirm(MemberVo memberVo) {
		System.out.println("[MemberController] signInConfirm() 요청 성공!");
		
		//서비스 호출
		MemberVo signInedMember = memberService.signInConfirm(memberVo);
		
		if(signInedMember != null) //로그인 성공
			return "sign_in_ok"; 
		else
			return "sign_in_ng"; //로그인 실패
		
		//return null;
	}
	
	
}	