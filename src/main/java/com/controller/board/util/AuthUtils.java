package com.controller.board.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.dto.MemberDTO;
import com.dto.board.IPost;
import com.dto.board.PostPageDTO;

@Component
public class AuthUtils {
	
    public boolean isUserLoggedIn(Map<String, String> paramMap) {
        String isLogin = paramMap.get("isLogin");
        return "yes".equals(isLogin);
    }
    
    public boolean isUserAuthorized(HttpSession session, IPost post) {
    	MemberDTO member = (MemberDTO)session.getAttribute("loginUser");
    	String userId = member.getUserId();
        return post.getUserId().equals(userId);
    }
    
	public void addUserLoginInfo(Map<String, String> paramMap , HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("loginUser");
		if (dto == null) {
			paramMap.put("isLogin", "no");
		} else {
			paramMap.put("isLogin", "yes");
			paramMap.put("nickname", dto.getNickname());
			paramMap.put("userId", dto.getUserId());
		}
	}
}
