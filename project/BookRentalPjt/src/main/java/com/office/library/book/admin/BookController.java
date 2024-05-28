package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.book.util.UploadFileService;

@Controller
@RequestMapping("/book/admin")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	UploadFileService uploadFileService;

	//도서 등록
	@GetMapping("/registerBookForm")
	public String registerBookForm() {
		System.out.println("[admin.BookController] registerBookForm() 호출!");

		String nextPage = "admin/book/register_book_form";

		return nextPage;
	}

	//도서 등록 확인
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[admin.BookController] registerBookConfirm() 호출!");

		String nextPage = "admin/book/register_book_ok";

		//파일 저장
		String savedFileName = uploadFileService.upload(file);

		if(savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);

			if(result <= 0)
				nextPage = "admin/book/register_book_ng";
		} else {
			nextPage = "admin/book/register_book_ng";
		}
		return nextPage;
	}

	//도서 검색
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo, Model model) {
		System.out.println("[admin.BookController] searchBookConfirm() 호출!");

		String nextPage = "admin/book/search_book";

		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);

		model.addAttribute("bookVos", bookVos);

		return nextPage;
	}

	//도서 상세보기
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[admin.BookController] bookDetail() 호출!");

		String nextPage = "admin/book/book_detail";

		BookVo bookVo = bookService.bookDetail(b_no);

		model.addAttribute("bookVo", bookVo);

		return nextPage;
	}


	//도서 수정
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[admin.BookController] modifyBookForm() 호출!");

		String nextPage = "admin/book/modify_book_form";

		BookVo bookVo = bookService.modifyBookForm(b_no);

		model.addAttribute("bookVo", bookVo);

		return nextPage;
	}
	
	//도서 수정 확인
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[admin.BookController] modifyBookConfirm() 호출!");
		
		String nextPage = "admin/book/modify_book_ok";
		
		if(!file.getOriginalFilename().equals("")) {
			
			//파일 저장
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null)
				bookVo.setB_thumbnail(savedFileName);
		}
		
		int result = bookService.modifyBookConfirm(bookVo); 

		if(result <= 0)
			nextPage = "admin/book/modify_book_ng";
		
		return nextPage;
	}

	//도서 삭제 확인
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no) {
		System.out.println("[admin.BookController] deleteBookConfirm() 호출!");

		String nextPage = "admin/book/delete_book_ok";

		int result = bookService.deleteBookConfirm(b_no); //DB에서 도서 정보를 삭제하기 위해 deleteBookConfirm()을 호출한다.

		if(result <= 0)
			nextPage = "admin/book/register_book_ng";
		
		return nextPage;
	}
	
	//대출 도서 목록
	@GetMapping("/getRentalBooks")
	public String getRentalBooks(Model model) {
		System.out.println("[admin.BookController] getRentalBooks() 호출!");
		
		String nextPage = "admin/book/rental_books";
		
		List<RentalBookVo> rentalBookVos = bookService.getRentalBooks();
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
	
	//도서 반납 확인
	@GetMapping("/returnBookConfirm")
	public String returnBookConfirm(@RequestParam("b_no") int b_no, @RequestParam("rb_no") int rb_no) {
		System.out.println("[admin.BookController] returnBookConfirm() 호출!");
		
		String nextPage = "admin/book/return_book_ok";
		
		int result = bookService.returnBookConfirm(b_no, rb_no);
		
		if(result <= 0)
			nextPage = "admin/book/return_book_ng";
		
		return nextPage;
	}
	
	//희망 도서 목록 조회
	@GetMapping("/getHopeBooks")
	public String getHopeBooks(Model model) {
		System.out.println("[admin.BookController] getHopeBooks() 호출!");
		
		String nextPage = "admin/book/hope_books";
		
		List<HopeBookVo> hopeBookVos = bookService.getHopeBooks();
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
	}
	
	//희망 도서 등록(입고 처리)
	@GetMapping("/registerHopeBookForm")
	public String registerHopeBookForm(Model model, HopeBookVo hopeBookVo) {
		System.out.println("[admin.BookController] registerHopeBookForm() 호출!");

		String nextPage = "admin/book/register_hope_book_form";
		
		model.addAttribute("hopeBookVo", hopeBookVo);
		
		return nextPage;
	}
	
	//희망 도서등록(입고 처리) 확인
	@PostMapping("/registerHopeBookConfirm")
	public String registerHopeBookConfirm(BookVo bookVo, @RequestParam("hb_no") int hb_no, @RequestParam("file") MultipartFile file) {
		System.out.println("[admin.BookController] registerHopeBookConfirm() 호출!");
		
		System.out.println("[admin.BookController] registerHopeBookConfirm() -> hb_no : " + hb_no);
		
		String nextPage = "admin/book/register_book_ok";
		
		//파일 저장
		String savedFileName = uploadFileService.upload(file); //표지 이미지를 서버에 저장한다.
		
		if(savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);

			int result = bookService.registerHopeBookConfirm(bookVo, hb_no);

			if(result <= 0)
				nextPage = "admin/book/register_book_ng";
		} else {
			nextPage = "admin/book/register_book_ng";
		}

		return nextPage;
	}
	
	//전체도서목록
	@GetMapping("/getAllBooks")
	public String getAllBooks(Model model) {
		System.out.println("[admin.BookController] getAllBooks() 호출!");

		String nextPage = "admin/book/full_list_of_books";
		
		List<BookVo> bookVos = bookService.getAllBooks();
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
}