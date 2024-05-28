package com.office.library.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalBookVo {

	//도서 렌탈 정보
	int rb_no;
    String u_m_no; 
    String rb_start_date;
    String rb_end_date;
    String rb_reg_date;
    String rb_mod_date;
	
	//도서 관련 정보
	int b_no;
	String b_thumbnail;
	String b_name;
	String b_author;
	String b_publisher;
	String b_publish_year;
	String b_isbn;
	String b_call_number;
	int b_rental_able;
	String b_reg_date;
	String b_mod_date;
	
	//관리자 회원 정보
	int a_m_no; //관리자 번호
	int a_m_approval; //최고 관리자 승인 여부
	String a_m_id; //관리자 ID
	String a_m_pw; //관리자 PW
	String a_m_name; //관리자명
	String a_m_gender; //관리자 설병 구분
	String a_m_part; //관리자 근무 부서
	String a_m_position; //관리자 업무
	String a_m_mail; //관리자 메일
	String a_m_phone; //관리자 연락처
	String a_m_reg_date; //관리자 등록일
	String a_m_mod_date; //관리자 수정일
	
	//유저 회원 정보
	String u_m_id;
	String u_m_pw;
	String u_m_name;
	String u_m_gender;
	String u_m_mail;
	String u_m_phone;
	String u_m_reg_date;
	String u_m_mod_date;
}
