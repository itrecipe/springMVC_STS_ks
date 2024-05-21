package com.office.library.admin.member;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0; //회원가입 실패(중복 ID)
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1; //회원가입 
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1; //회원가입 실패(DB insert 실패)

	@Autowired
	AdminMemberDao adminMemberDao;

	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] createAccountConfirm() 요청 !");

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
		System.out.println("[AdminMemberService] loginConfirm() 요청!");

		AdminMemberVo loginedAdminMemberVo =
				adminMemberDao.selectAdmin(adminMemberVo);

		if(loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN SUCCESS!!");
		else 
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN FAIL!!");

		return loginedAdminMemberVo;
	}

	public List<AdminMemberVo> listupAdmin() {
		System.out.println("[AdminMemberService] listupAdmin() 요청!");

		//dao의 selectAdmins()를 아직 생성하지 않아서 에러가 나오고 있으니 생성해줄것
		return adminMemberDao.selectAdmins();
	}

	public void setAdminApproval(int a_m_no) {
		System.out.println("[AdminMemberService] setAdminApproval() 요청!");

		int result = adminMemberDao.updateAdminAccount(a_m_no);
	}

	public int modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] modifyAccountConfirm() 요청!");

		return adminMemberDao.updateAdminAccount(adminMemberVo);
	}

	public AdminMemberVo getLoginedAdminMemberVo(int a_m_no) {
		System.out.println("[AdminMemberService] getLoginedAdminMemberVo() 요청!");

		return adminMemberDao.selectAdmin(a_m_no);
	}

	//비번 찾기 확인(새로운 비번 생성)
	public int findPasswordConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] findPasswordConfirm() 요청!");

		AdminMemberVo selectedAdminMemberVo =
				adminMemberDao.selectAdmin(
						adminMemberVo.getA_m_id(),
						adminMemberVo.getA_m_name(),
						adminMemberVo.getA_m_mail()
						);
		
		System.out.println("findPasswordConfirm() -> selectedAdminMemberVo : " + selectedAdminMemberVo);

		int result = 0;

		if (selectedAdminMemberVo != null) {
			String newPassword = createNewPassword();
			System.out.println("findPasswordConfirm() -> newPassword" + newPassword);
			result = adminMemberDao.updatePassword(adminMemberVo.getA_m_id(), newPassword);

			if(result > 0)
				sendNewPasswordByMail(adminMemberVo.getA_m_mail(), newPassword);
			System.out.println("findPasswordConfirm() -> result : " + result);
		}
		return result;
	}

	private String createNewPassword() {
		System.out.println("[AdminMemberService] createNewPassword() 요청!");

		char[] chars = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z' 
			};
		
		System.out.println("createNewPassword() -> chars : " + chars);
		
		StringBuffer stringBuffer = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime());

		int index = 0;
		int length = chars.length;

		for(int i = 0; i < 8; i++) {
			index = secureRandom.nextInt(length);

			if(index % 2 == 0)
				stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
			else
				stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
		}

		System.out.println("[AdminMemberService] NEW PASSWORD : " + stringBuffer.toString());

		return stringBuffer.toString();
	}
	
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		System.out.println("[AdminmemberService] sendNewPasswordByMail()");
		
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper
				
				= new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo("itrecipe95@gmail.com");
//				mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[한국도서관] 새 비밀번호 안내입니다.");
				mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
			}
		};
		javaMailSenderImpl.send(mimeMessagePreparator);
	}
}