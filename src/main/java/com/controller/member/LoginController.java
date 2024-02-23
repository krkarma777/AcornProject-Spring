package com.controller.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.service.member.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService serv;

	//로그인
	@RequestMapping(value = "/Logined", method = RequestMethod.POST)
	public String LoginToMypage(String userId, String userPw, HttpSession session) {
		MemberDTO dto = serv.login(userId, userPw);

		if (dto != null) {
			session.setAttribute("loginUser", dto);
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}

	//로그아웃
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String Logout(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
		if (dto != null) {
			session.removeAttribute("loginUser");
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//아이디 찾기
	@RequestMapping(value = "/SearchID", method = RequestMethod.POST)
	public String SearchID(Model model, String userName, String ssn1, String ssn2) {
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);
		System.out.println(dto);
		if (dto != null) {
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 찾기
	@RequestMapping(value = "/SearchPartPW", method = RequestMethod.POST)
	public String SearchPartPW(Model model, HttpServletResponse response, String userId, String userName, String ssn1, String ssn2) {
		MemberDTO dto = serv.findUserPW(userId, userName, ssn1, ssn2);
		if (dto != null) {
			Cookie userIdCookie = new Cookie("findPW_userid",userId);
			userIdCookie.setMaxAge(30*60);
			response.addCookie(userIdCookie);
			
			model.addAttribute("dto", dto);
			return "member/Find_Info/viewPartPW";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}
	}
	
	//전체 비밀번호 출력용
	@RequestMapping(value = "/SearchAllPW", method = RequestMethod.GET)
	public String SearchAllPW(Model model, String userId, HttpSession session) {
		MemberDTO dto = serv.selectMemberData(userId);
		if (dto != null) {
			model.addAttribute("dto", dto);
			
			//디버그 코드************************************************************************
			return "member/Find_Info/viewAllPW";
			//디버그 코드******
						
//			RequestDispatcher dis = request.getRequestDispatcher("SendEmailServlet");
//			dis.forward(request, response);
						
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	
	
	
	
	
	
}
