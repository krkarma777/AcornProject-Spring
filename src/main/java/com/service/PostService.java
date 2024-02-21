package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PostDAO;
import com.dto.MemberDTO;
import com.dto.board.LikeDTO;
import com.dto.board.PageDTO;
import com.dto.board.PostDTO;
import com.dto.board.PostPageDTO;
import com.dto.board.PostSaveDTO;

@Service
@Transactional
public class PostService {

	@Autowired
	PostDAO dao;

	// 글 추가
	public Long insertContent(PostDTO post) {
		dao.insertContent(post);
		return post.getPostId();
	}

	public Long viewPlus(PostDTO post) {
		dao.insertContent(post);
		return post.getPostId();
	}

	// 글 조회
	public PostDTO select(Long postId) {
		return dao.select(postId);

	}

	public PageDTO<PostPageDTO> getPostsByPage(HashMap<String, Object> map) {
		return dao.selectByPage(map);

	}

	// 모든 글 조회
	public List<PostPageDTO> selectAll(HashMap<String, String> hashMap) {
		return dao.selectAll(hashMap);
	}

	public List<PostPageDTO> popularPostTwoDays(HashMap<String, String> hashMap) {
		return dao.popularPostTwoDays(hashMap);
	}

	// 글 수정
	public void update(Long postId, String updatedTitle, String updatedContent, Long postCategory) {
		dao.updateContent(postId, updatedTitle, updatedContent, postCategory);
	}

	// 글 삭제
	public void delete(Long postId) {
		dao.delete(postId);
	}

	// 게시물 조회수 업데이트
	public int updateViewNum(Long postId) {
		return dao.updateViewNum(postId);
	}// end updateVieNum

	public Long likeNum(Long postId) {
		return dao.likeNum(postId);
	}

	public Long viewNum(Long postId) {
		return dao.viewNum(postId);
	}

	public PostPageDTO selectPagePost(Long postId) {
		return dao.selectPagePost(postId);
	}

	public int postLike(HashMap<String, String> map) {
		return dao.postLike(map);
	}

	public int updatePostLike(HashMap<String, String> map) {

		try {
			LikeDTO lDto = dao.selectPostLike(map);
			if (lDto != null) {
				dao.updatePostLike(map);
			} else {
				dao.postLike(map);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public MemberDTO selectMember(String userId) {
		return dao.selectMember(userId);
	}

	public void insertPostSave(PostSaveDTO dto) {
		dao.insertPostSave(dto);

	}//

	// 임시저장글 select
	public List<PostSaveDTO> postSaveSelect(String userId) {
		return dao.postSaveSelect(userId);
	}

	// 임시저장글 delete
	public void deletePostSave(String postSaveId) {
		dao.deletePostSave(postSaveId);

	}

	public PostSaveDTO selectPostSave(String postSaveId) {
		PostSaveDTO postsave = dao.selectPostSave(postSaveId);
		return postsave;
	}

}
