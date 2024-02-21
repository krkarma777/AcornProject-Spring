package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ReviewDAO;
import com.dto.CommentDTO;
import com.dto.ContentDTO;
import com.dto.RateDTO;
import com.dto.ReportDTO;
import com.dto.ReviewDTO;

@Service
public class ReviewService {

	@Autowired
	ReviewDAO dao;
	
	public ReviewDTO writeReview(ReviewDTO review) {
		review = dao.writeReview(review);
		return review;
	}

	public ReviewDTO SelectReviewByUser(HashMap<String, String> map) {
		ReviewDTO review = dao.SelectReviewByUser(map);
		return review;
	}

	public void UpdateScore(RateDTO dto) {
		dao.UpdateScore(dto);
	}

	public List<ReviewDTO> selectReviews(HashMap<String, String> map) {
		List<ReviewDTO> reviewList = dao.selectReviews(map);
		return reviewList;
	}

	public ContentDTO selectContent(String contId) {
		ContentDTO content = dao.selectContent(contId);
		
		return content;
	}

	public void UpdateLike(HashMap<String, String> map) {
		dao.UpdateLike(map);
	}

	public ReviewDTO selectReviewByPostId(String postId) {
		ReviewDTO review = dao.selectReviewByPostId(postId);
		return review;
	}

	public List<CommentDTO> selectComments(String postId) {
		List<CommentDTO> comments = dao.selectComments(postId);
		return comments;
	}

	public List<RateDTO> selectRates(String contId) {
		List<RateDTO> rateList = dao.selectRates(contId);

		return rateList;
	}

	public ReviewDTO selectReview(HashMap<String, String> map) {
		ReviewDTO review = dao.selectReview(map);
		return review;
	}

	public void reportReview(ReportDTO report) {
		dao.reportReview(report);
	}
}
