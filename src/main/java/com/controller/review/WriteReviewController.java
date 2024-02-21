package com.controller.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MemberDTO;
import com.dto.ReviewDTO;
import com.service.ReviewService;

@Controller
public class WriteReviewController {

	@Autowired
	ReviewService service;
	
	// 비동기 리뷰 작성 서블릿
	@RequestMapping(value="my-review", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView writeReview(ReviewDTO review, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/contentViewer");
		
		// 세션에서 로그인 정보 파싱
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		
		//로그인 정보가 존재하지 않을 때
		if(login==null) {
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			
		// 로그인 정보가 존재할 때
		} else {
			
			// DB에 등록, 등록된 review객체 받아옴
			review = service.writeReview(review);
			
			// javascript에서 텍스트를 JSON.parse()로 json객체로 변환하여 사용할 수 있게하기 위해 하는 작업
			String jsonText = "{"
					+ "\"postId\": \""+review.getPostId()+"\","
					+ "\"postText\": \""+review.getPostBoard()+"\","
					+ "\"userId\": \""+review.getUserId()+"\","
					+ "\"contId\": \""+review.getContId()+"\","
					+ "\"postTitle\": \""+review.getPostTitle()+"\","
					+ "\"postDate\": \""+review.getPostDate()+"\","
					+ "\"editDate\": \""+review.getEditDate()+"\","
					+ "\"postText\": \""+review.getPostText()+"\","
					+ "\"nickname\": \""+review.getNickname()+"\""
					+ "}";
			//응답
			mav.addObject(jsonText);
		}

		return mav;
	}
}
