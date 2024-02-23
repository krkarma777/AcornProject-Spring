package com.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.member.LoginService;
import com.service.member.RegisterService;

@RestController
public class AjaxController {

	@Autowired
	LoginService lServ;

	@Autowired
	RegisterService rServ;

	
	//***************************************************************************************************************
	//***************************************************로 그 인*******************************************************
	//***************************************************************************************************************
	
	//메인에서 로그인 여부 확인 에이젝스
	@RequestMapping(value = "AjaxCheckIDPW", method = RequestMethod.POST)
	public String AjaxCheckIDPW(String userId, String userPw) {
		boolean canLogin = lServ.loginPossible(userId, userPw);
		String mesg = "loginSuccess";
		if (!canLogin) {
			mesg = "loginFail";                
        }
		return mesg;
	}
	
	//전체 비밀번호 찾기에서 질문에 따른 대답 확인 에이젝스
	@RequestMapping(value = "AjaxMatchQnA", method = RequestMethod.POST)
	public String AjaxMatchQnA(String userInfo, String answer, String userId) {
		
		boolean can_All_PW = false;
		String mesg = "correct_Answer";
																        //디버그 코드*****************************
																        System.out.println(userInfo);
																        System.out.println(answer);
																        System.out.println(userId);
																        //*************************************
        
		// 선택된 질문에 따라 사용되는 Method 변경**************************
		if (userInfo.equals("nickname")) {
		can_All_PW = lServ.findPWbyNickname(answer, userId);
		} else if (userInfo.equals("userPhoneNum")) {
		can_All_PW = lServ.findPWbyPhoneNum(answer, userId);
		} else if (userInfo.equals("userEmail")) {
		can_All_PW = lServ.findPWbyEmail(answer, userId);
		}
		// 선택된 질문에 따라 사용되는 Method 변경**************************
																		//디버그 코드*****************************                 
																		System.out.println(can_All_PW);    
																		//*************************************
																		
	    //사용자 ID와 질문과 답변이 일치하지 않을 경우, ajax출력																			
		if (can_All_PW == false){
	        mesg = "wrong_Answer";
	    }						

		//사용자 ID와 질문과 답변이 일치할 경우, ajax출력
		return mesg;
			
	}
	
	
	
	//***************************************************************************************************************
	//***************************************************회원 가입*******************************************************
	//***************************************************************************************************************
	
	//회원가입 자식창에서 아이디 중복 에이젝스
	@RequestMapping(value = "AjaxIDDuplicate", method = RequestMethod.POST)
	public String AjaxIDDuplicate(String userId) {
		boolean isDuplicate = rServ.isUserIdDuplicate(userId);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}
	
	//회원가입 자식창에서 닉네임 중복 에이젝스
	@RequestMapping(value = "AjaxNicknameDuplicate", method = RequestMethod.POST)
	public String AjaxNicknameDuplicate(String nickname) {
		boolean isDuplicate = rServ.isUserNicknameDuplicate(nickname);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}
		
	//회원가입 자식창에서 이메일 중복 에이젝스
	@RequestMapping(value = "AjaxEmailDuplicate", method = RequestMethod.POST)
	public String AjaxEmailDuplicate(String userEmailId, String userEmailDomain) {
		boolean isDuplicate = rServ.isUserEmailDuplicate(userEmailId, userEmailDomain);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}		
	
	//회원가입 자식창에서 핸드폰 번호 중복 에이젝스
	@RequestMapping(value = "AjaxPhoneNumDuplicate", method = RequestMethod.POST)
	public String AjaxPhoneNumDuplicate(String userPhoneNum1, String userPhoneNum2, String userPhoneNum3) {
		boolean isDuplicate = rServ.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);
		String mesg = "notDuplicate";
		if (isDuplicate) {
			mesg = "duplicate"; 
        } 
		return mesg;
	}	
}
