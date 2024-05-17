package com.office.library.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {
	
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0; //회원가입 실패(중복 ID)
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1; //회원가입 성공
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1; //회원가입 실패(DB insert 실패)

	@Autowired
	AdminMemberDao adminMemberDao;

	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] createAccountConfirm() 요청 성공!");
		
		boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
		
		System.out.println(isMember);
		
		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberVo);
			
			if (result > 0)
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
			else
				return ADMIN_ACCOUNT_CREATE_FAIL;
		} else {
			return ADMIN_ACCOUNT_ALREADY_EXIST;
		}
	}
	
	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] loginConfirm() 요청 성공!");
		
		AdminMemberVo loginedAdminMemberVo =
				adminMemberDao.selectAdmin(adminMemberVo);
		
		if(loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN SUCCESS!!");
		else 
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN FAIL!!");
		
		return loginedAdminMemberVo;
	}
	
	public List<AdminMemberVo> listupAdmin() {
		System.out.println("[AdminMemberController] listupAdmin()");
	
		//dao의 selectAdmins()를 아직 생성하지 않아서 에러가 나오고 있으니 생성해줄것
		return adminMemberDao.selectAdmins();
	}
}