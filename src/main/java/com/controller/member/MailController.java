package com.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.member.MailService;

@Controller
public class MailController {

	@Autowired
	private MailService mailService;


	//DB작업이 필요한 만큼 DAO들 선언해야함
	
	//아이디와 이메일이 같으면 특정한 메일이 가게..
	@RequestMapping("/noticeMail")
	public ModelAndView sendEmail(String id, String email) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String addr = "cjstkrhdfk666@gmail.com";
		
		String subject = "[123] 알림메일 입니다.";
		
		String body = "안녕하세요?\r\n소통해요~\r\n SMTP메일 테스트입니다.";
		
		mailService.sendEmail(email, addr, subject, body);
		
		mv.setViewName("/");
		return mv;
	}
}
	
	
	
	
	
