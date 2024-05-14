package com.company.hello.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	//@RequestMapping("/signUp") : signUp을 매핑하여 요청해주는 놈
	@RequestMapping("/signUp")
	public String signUp() {
		System.out.println("sign_up.jsp 요청 성공!");
		return "sign_up"; //매핑되어 요청받으면 sign_up.jsp 파일을 반환하여 view에 뿌려줄 수 있음
	}
	
	@RequestMapping("/signIn")
	public String signIn() {
		System.out.println("sign_in.jsp 요청 성공!");
		return "sign_in";
	}
}