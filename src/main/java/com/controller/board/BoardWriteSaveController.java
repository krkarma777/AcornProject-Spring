package com.controller.board;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MemberDTO;
import com.dto.board.PostSaveDTO;
import com.service.PostService;

@Controller
@RequestMapping("/post")
public class BoardWriteSaveController {
	

	PostSaveDTO dto;
	@Autowired
	PostService service;
	
	//임시글 저장
	@PostMapping("/save")
	@ResponseBody
	public String insertPostSave(PostSaveDTO dto, HttpSession session) {
		MemberDTO mdto = (MemberDTO)session.getAttribute("loginUser");
		String userId = mdto.getUserId();
		dto.setUserId(userId);
		
		System.out.println("임시저장 dto => "+dto);
		service.insertPostSave(dto);
		
		return "redirect:post";
	}//
	
	//임시글 불러오기
	@PostMapping("/saveSelect")
	@ResponseBody
	public PostSaveDTO selectPostSave(String postSaveId) {
    	System.out.println("임시글 불러오기 id = "+postSaveId);
    	PostSaveDTO postSave = service.selectPostSave(postSaveId);
    	
		return postSave;
	}
	
	//임시글 삭제
	@PostMapping("/saveDelete")
	@ResponseBody
	public ResponseEntity<String> deletePostSave(Long postSaveId, HttpSession session) {
		System.out.println("삭제 => "+postSaveId);
		service.deletePostSave(postSaveId);
		
		return ResponseEntity.ok("성공했습니다.");
	}
	
	@PostMapping("/saveList")
//	@ResponseBody
	public void saveList(HttpSession session, Model m) {
		MemberDTO member = (MemberDTO)session.getAttribute("loginUser");
        String userId = member.getUserId();
		List<PostSaveDTO> postSaveList = service.listPostSave(userId);
		System.out.println("임시저장 목록 => "+postSaveList);
		m.addAttribute("postSaveList", postSaveList);
	}
	

}//end class
