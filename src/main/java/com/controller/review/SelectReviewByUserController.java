package com.controller.review;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.ReviewDTO;
import com.service.ReviewService;

@Controller
public class SelectReviewByUserController {
	@Autowired
	ReviewService service;
	
	@RequestMapping(value="/my-review", method=RequestMethod.GET)
	@ResponseBody
	public String SelectReviewByUser(@RequestParam HashMap<String, String> map) {
		
		ReviewDTO review = service.SelectReviewByUser(map);
		String jsonText = null;
		if(review!=null) {
			int score=0;
			if(review.getScore()!=null) {
				score = Integer.parseInt(review.getScore());
			}

			jsonText = "{"
					+ "\"postId\": \""+review.getPostId()+"\","
					+ "\"postText\": \""+review.getPostBoard()+"\","
					+ "\"userId\": \""+review.getUserId()+"\","
					+ "\"contId\": \""+review.getContId()+"\","
					+ "\"postTitle\": \""+review.getPostTitle()+"\","
					+ "\"postDate\": \""+review.getPostDate()+"\","
					+ "\"editDate\": \""+review.getEditDate()+"\","
					+ "\"postText\": \""+review.getPostText()+"\","
					+ "\"nickname\": \""+review.getNickname()+"\","
					+ "\"score\": \""+score+"\""
					+ "}";
		}
		return jsonText;
	}
}
