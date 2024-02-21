package com.controller.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.dto.RateDTO;
import com.service.ReviewService;

@Controller
public class UpdateScoreController {

	@Autowired
	ReviewService service;
	
	// 비동기 별점 업데이트 서블릿
	@RequestMapping(value="/score", method=RequestMethod.POST)
	@ResponseBody
	public String UpdateScore(RateDTO rate, HttpSession session) {
		
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		// 로그인 정보가 존재할 때
		if(login!=null) {

			//DB에 별점 업데이트 작업
			ReviewService service = new ReviewService();
			service.UpdateScore(rate);
			
			//반환값 따로 없음
		}	
		return "review/contentViewer";
	}
}
