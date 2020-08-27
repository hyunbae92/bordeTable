package com.table.borde.service;

import java.util.List;

import com.table.borde.vo.PostVO;

public interface PostService {
	
	int doInsertPost(PostVO post);
	int doUpdatePost(PostVO post);
	int doDeletePost(int postNo);
	PostVO doSelectPost(int postNo);
	List<PostVO> doSelectPostList(List<PostVO> post);
}
