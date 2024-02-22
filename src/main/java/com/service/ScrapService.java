package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dao.ScrapDAO;
import com.dto.board.ScrapDTO;

@Service
public class ScrapService {
	
	@Autowired
	ScrapDAO dao;


	public void insert(HashMap<String, String> map) {
		dao.insert(map);
	}

	public ScrapDTO checkScrap(HashMap<String, String> map) {
		ScrapDTO scrapDTO = dao.checkScrap(map);
		return scrapDTO;
		
	}



	

}
