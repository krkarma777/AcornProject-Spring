package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ContentDTO;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public List<ContentDTO> selectTop() {
		List<ContentDTO> movieTopList = session.selectList("ReviewMapper.selectTop");
		return movieTopList;
	}
}
