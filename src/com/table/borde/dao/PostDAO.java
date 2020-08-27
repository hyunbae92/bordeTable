package com.table.borde.dao;

import java.util.List;

import com.table.borde.vo.PostVO;

public interface PostDAO {
	
	int insertPost(PostVO post);
	int updatePost(PostVO post);
	int deletePost(int postNo);
	PostVO selectPost(int postNo);
	List<PostVO> selectPostList(List<PostVO> post);
}
