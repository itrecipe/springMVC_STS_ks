package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.admin.member.AdminMemberVo;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

	@Autowired
	UserMemberService userMemberService;

	//회원가입
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[user.member.UserMemberController] createAccountForm()!");

		String nextPage = "user/member/create_account_form";

		return nextPage;
	}

	//회원가입 확인
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[user.member.UserMemberController] createAccountConfirm() 요청 성공!");

		//서비스를 주입 받은 후 추가한 코드
		String nextPage = "user/member/create_account_ok";

		int result = userMemberService.createAccountConfirm(userMemberVo);

		if(result <= 0)
			nextPage = "user/member/create_account_ng";
		return nextPage;
	}

	//로그인
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("[user.member.UserMemberController] loginForm()!");

		String nextPage = "user/member/login_form";

		return nextPage;
	}

	//로그인 확인
	@PostMapping("/loginConfirm")
	public String loginConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] loginConfirm()");

		String nextPage = "user/member/login_ok";

		UserMemberVo loginedUserMemberVo =
				userMemberService.loginConfirm(userMemberVo);

		if (loginedUserMemberVo == null) {
			nextPage = "admin/member/login_ng";
		} else {
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}

		return nextPage;
	}

	//계정 수정
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountForm() 요청!");

		String nextPage = "user/member/modify_account_form";

		/* 리다이렉트는 페이지 리로드가 많아지면 뻗기 때문에 인터셉터로 대체
		UserMemberVo loginedUserMemberVo = 
				(UserMemberVo) session.getAttribute("loginedUserMemberVo");

		if(loginedUserMemberVo == null) 
			nextPage = "redirect:/admin/user/loginForm";
		*/
		return nextPage;
	}

	//회원 정보 수정 확인
	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] modifyAccountConfirm() 요청!");

		String nextPage = "user/member/modify_account_ok";

		int result = userMemberService.modifyAccountConfirm(userMemberVo);

		if(result > 0) {
			UserMemberVo loginedUserMemberVo =
					userMemberService.getLoginedUserMemberVo(userMemberVo.getU_m_no());

			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		} else {
			nextPage = "user/member/modify_account_ng";
		}

		return nextPage;
	}
	
	//로그아웃 확인
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println("[UserMemberController] logoutConfirm()");
		
		String nextPage = "redirect:/";
		
		session.invalidate(); //invalidate 세션을 무효화 시키는 메서드
		
		return nextPage;
	}
	
	//비번 찾기 폼
	@GetMapping("/findPasswordForm")
	public String findPasswordForm() {
		System.out.println("[user.member.UserMemberController] findPasswordForm() 요청!");
		
		String nextPage = "user/member/find_password_form";
		
		return nextPage;
	}	
	
	//비번 찾기 확인
	@PostMapping("/findPasswordConfirm")
	public String findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[user.member.UserMemberController] findPasswordConfirm() 요청!");
		
		String nextPage = "user/member/find_password_ok";
		
		int result = userMemberService.findPasswordConfirm(userMemberVo);
		
		if(result <= 0)
			nextPage = "user/member/find_password_ng";
		
		return nextPage;
	}
	
	
}