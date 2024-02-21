package com.controller.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.dto.ReportDTO;
import com.service.ReviewService;

@Controller
public class ReportController {

	@Autowired
	ReviewService service;
	
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public String Report(ReportDTO report,HttpSession session, RedirectAttributes flash) {
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {
			String userId = login.getUserId();
			// dto에 담아서 전달
			report.setUserId(userId);

			// db에 신고정보 저장
			ReviewService service = new ReviewService();
			service.reportReview(report);
			
			// 완료메세지
			flash.addFlashAttribute("mesg", "해당 리뷰를 신고하였습니다.");
		}

		return "redirect:ShowReviewController";
	}
}
