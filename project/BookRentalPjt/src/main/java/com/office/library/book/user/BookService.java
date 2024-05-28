package com.office.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	public List<BookVo> searchBookConfirm(BookVo bookVo) {
		System.out.println("[user.BookService] searchBookConfirm()");
		
		return bookDao.selectBooksBySearch(bookVo);
	}
	
	public BookVo bookDetail(int b_no) {
		System.out.println("[user.BookService] bookDetail()!");
		
		return bookDao.selectBook(b_no);
	}
	
	public int rentalBookConfirm(int b_no, int u_m_no) {
		System.out.println("[user.BookService] rentalBookConfirm()!");
		
		int result = bookDao.insertRentalBook(b_no, u_m_no); //dao에 대출 이력을 추가한다.
		
		if(result >= 0)
			bookDao.updateRentalBookAble(b_no);
		return result;
	}
	
	public List<RentalBookVo> enterBookshelf(int u_m_no) {
		System.out.println("[user.BookService] rentalBookConfirm()!");

		//dao에 접근하여 selectRentalBooks() 메서드를 이용해 대출한 도서를 검색한다, 파라미터는 u_m_no 값을 전달 받는다.
		return bookDao.selectRentalBooks(u_m_no);
	}
	
	public List<RentalBookVo> listupRentalBookHistory(int u_m_no) {
		System.out.println("[user.BookService] listupRentalBookHistory()!");
		
		return bookDao.selectRentalBookHistory(u_m_no);
		
	}
	
	public int requestHopeBookConfirm(HopeBookVo hopeBookVo) {
		System.out.println("[user.BookService] requestHopeBookConfirm()!");
		
		return bookDao.insertHopeBook(hopeBookVo);
	}
	
	public List<HopeBookVo> listupRequestHopeBook(int u_m_no) {
		System.out.println("[user.BookService] listupRequestHopeBook()!");
		
		return bookDao.selectRequestHopeBooks(u_m_no);
	}
}