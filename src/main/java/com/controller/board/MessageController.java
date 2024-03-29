package com.controller.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.board.MessageDTO;
import com.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	MessageService memberService;
	
	
	@GetMapping("/board/note")
	public String messageForm(HttpSession session, Model model) {
		
		MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
        String senderId = dto.getUserId();

    	List<MessageDTO> sList = memberService.selectSendedMessage(senderId);
		model.addAttribute("sendedMessage",sList);
		
		List<MessageDTO> rList = memberService.selectReceivedMessage(senderId);
		model.addAttribute("receivedMessage", rList);

		return "board/message";
	}
	
		// post
		@PostMapping("/board/note")
		public String messageForm(@RequestParam("receiverId")String receiverId,
				@RequestParam("messageContent")String messageContent,
				HttpSession session) {
			
			MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
			String senderId = dto.getUserId();
			
			HashMap<String, String> map = new HashMap<>(); 
			map.put("senderId", senderId);
			map.put("receiverId", receiverId);
			map.put("messageContent", messageContent);
		
	
			memberService.insert(map);
			
			return "redirect:note";
		}


}
