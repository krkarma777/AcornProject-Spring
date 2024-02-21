package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.dto.board.ScrapDTO;

@Repository
public class ScrapDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public int insert(HashMap<String, String> map) {
		int n = session.insert("ScrapMapper.insert",map);
		return n;
	}

	public ScrapDTO checkScrap(HashMap<String, String> map) {
		ScrapDTO scrapDTO = session.selectOne("ScrapMapper.checkScrap",map);
		return scrapDTO;
	}

}
