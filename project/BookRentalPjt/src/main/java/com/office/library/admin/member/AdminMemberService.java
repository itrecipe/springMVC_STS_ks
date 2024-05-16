package com.office.library.admin.member;

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
}
