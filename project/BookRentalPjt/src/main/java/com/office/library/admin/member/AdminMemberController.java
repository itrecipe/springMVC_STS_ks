package com.office.library.admin.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	//회원가입
	//@RequestMapping(value = "/createAccountForm", method = RequestMethod.GET)
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[AdminMemberController] createAccountForm() 요청 성공!");
		
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
	}
	
	//회원가입 확인
	//@RequestMapping(value = "/createAccountConfirm", method = RequestMethod.POST)
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] createAccountConfirm() 요청 성공!");

		//서비스를 주입 받은 후 추가한 코드
		String nextPage = "admin/member/create_account_ok";
		
		int result = adminMemberService.createAccountConfirm(adminMemberVo);
		
		if(result <= 0)
			nextPage = "admin/member/create_account_ng";
		return nextPage;
	}

	//로그인
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[AdminMemberController] loginForm() 요청 성공!");
		
		String nextPage = "admin/member/login_form";
		
		return nextPage;
	}
	
	//쿠키는 클라이언트 관련, 세션은 백엔드 관련
	
	//로그인 확인
	@PostMapping("/loginConfirm")
	public String loginConfirm(AdminMemberVo adminMemberVo, HttpSession session) {
		System.out.println("[AdminMemberController] loginConfirm() 요청 성공!");
		
		String nextPage = "admin/member/login_ok";
		
		AdminMemberVo loginedAdminMemberVo =
				adminMemberService.loginConfirm(adminMemberVo);
		
		if (loginedAdminMemberVo == null) {
			nextPage = "admin/member/login_ng";
		} else {
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}

		return nextPage;
	}
	
	//로그아웃 확인
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println("[AdminMemberController] logoutConfirm()");
		
		String nextPage = "redirect:/admin";
		
		session.invalidate(); //invalidate 세션을 무효화 시키는 메서드
		
		return nextPage;
	}
	
	//관리자 목록
	@RequestMapping(value = "/listupAdmin", method = RequestMethod.GET)
	public String listupAdmin(Model model) {
		System.out.println("[AdminMemberController] listupAdmin()");
		
		String nextPage = "admin/member/listup_admins";
		
		List<AdminMemberVo> adminMemberVos = adminMemberService.listupAdmin();
		
		model.addAttribute("adminMemberVos", adminMemberVos);
		
		return nextPage;
	}
	

}