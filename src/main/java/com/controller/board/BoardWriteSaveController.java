package com.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.board.PostSaveDTO;
import com.service.PostService;

@Controller
public class BoardWriteSaveController {
	
	@Autowired
	PostSaveDTO dto;
	@Autowired
	PostService service;
	
	//임시글 저장
	@PostMapping("/board/save")
	public String insertPostSave(PostSaveDTO dto) {
		System.out.println("임시저장 dto => "+dto);
//		PostSaveDTO dto = new PostSaveDTO(null, userId, postTitle, postText, null);
		service.insertPostSave(dto);
		
		return "redirect:post";
	}//
	
	//임시글 불러오기
	@PostMapping("/board/saveSelect")
	@ResponseBody
	public PostSaveDTO selectPostSave(String postSaveId) {
    	System.out.println("임시글 불러오기 id = "+postSaveId);
    	PostSaveDTO postSave = service.selectPostSave(postSaveId);
    	
		return postSave;
	}
	
	//임시글 삭제
	@PostMapping("/board/saveDelete")
	public String deletePostSave(String postSaveId) {
		
		service.deletePostSave(postSaveId);
		
		return "redirect:post";
	}
	

	

}//end class
