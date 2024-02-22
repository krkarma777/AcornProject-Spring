package com.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MemberDTO;
import com.service.member.LoginService;
import com.service.member.RegisterService;

@Controller
public class TestController {

	@Autowired
	LoginService serv;

	//멤버 리스트 찾기
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ModelAndView memberList() {
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> list = serv.selectAll();
		mav.addObject("memberList", list);
		mav.setViewName("member/Test/test_view_list");
	
		return mav;
	}
}
