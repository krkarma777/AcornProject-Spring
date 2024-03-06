package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AdminMemberDTO;
import com.dto.AdminRestrictedMemberDTO;

@Repository
public class AdminMemberDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public List<AdminMemberDTO> SearchMember(HashMap<String, String> map) {
		List<AdminMemberDTO> list = session.selectList("AdminMemberMapper.SearchMember", map);
		return list;
	}

	public List<AdminRestrictedMemberDTO> getRestrictedMemberList() {
//		System.out.println("dao 시작함");
		List<AdminRestrictedMemberDTO> list = session.selectList("AdminMemberMapper.getRestrictedMemberList");
//		System.out.println("매퍼 다녀옴");
		System.out.println(list);
		return list;
	}

}
