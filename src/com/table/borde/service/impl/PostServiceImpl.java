package com.table.borde.service.impl;

import java.util.List;

import com.table.borde.dao.PostDAO;
import com.table.borde.dao.impl.PostDAOImpl;
import com.table.borde.service.PostService;
import com.table.borde.vo.PostVO;

public class PostServiceImpl implements PostService {
	private PostDAO postDAO = new PostDAOImpl();
	@Override
	public int doInsertPost(PostVO post) {
		return postDAO.insertPost(post);
	}

	@Override
	public int doUpdatePost(PostVO post) {
		int result = postDAO.updatePost(post);
		if(result==1) {
			post.setPostUpdateCnt(post.getPostUpdateCnt()+1);
			return postDAO.updatePost(post); 
		}
		return 0;
	}

	@Override
	public int doDeletePost(int postNo) {
		return postDAO.deletePost(postNo);
	}

	@Override
	public PostVO doSelectPost(int postNo) {
		return postDAO.selectPost(postNo);
	}

	@Override
	public List<PostVO> doSelectPostList(List<PostVO> post) {
		return postDAO.selectPostList(post);
	}

}
