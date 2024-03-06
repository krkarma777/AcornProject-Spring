package com.controller.adminpage.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.AdminRestrictedMemberDTO;
import com.dto.AdminRprtdDTO;
import com.service.adminpage.AdminMemberService;
import com.service.adminpage.AdminReportService;

@Controller
public class AdminPageMemberController {

	@Autowired
	AdminMemberService mservice;
	
	@Autowired
	AdminReportService rservice;
	
	//신고회원 조회
	@RequestMapping("/AdminPage/AdminMemberReported")
	public ModelAndView SearchReportedMember(String SearchValue, String criteria, ModelAndView mav) {
		//System.out.println("in AdminPageMemberController:SearchReportedMember()");
		HashMap<String,String> map = new HashMap<>();
		map.put("SearchValue", SearchValue);
		map.put("criteria", criteria);
		
		List<AdminRprtdDTO> list = rservice.ReportedMemList(map);
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageMemRprtedMem");
		return mav;
	}
	
	//신고글 삭제기능
	@RequestMapping(value = "")
	public String deleteReportedPost() {
		return "";
	}
	
	//이용제한 사용자 리스트 조회
	@RequestMapping(value = "/AdminPage/RestrictedMemberList")
	public ModelAndView getRestrictedMemberList(ModelAndView mav, String SearchValue, String criteria) {
		
		System.out.println("in AdminMemberController.getRestrictedMemberList()");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("SearchValue", SearchValue);
		map.put("criteria", criteria);
		System.out.println(map);
		
//		System.out.println("간다");
		List<AdminRestrictedMemberDTO> list = mservice.getRestrictedMemberList();
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("/AdminPage/AdminPageMemRestricted");
		return mav;
	}
	
	//삭제된 회원 데이터 관리
	@RequestMapping(value = "/AdminPage/toAdminPageDeletedMember")
	public String toAdminPageMonitoring() {
		return "/AdminPage/AdminPageDeletedMember";
	}
}
