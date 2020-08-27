package com.table.borde.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.table.borde.dao.PostDAO;
import com.table.borde.servlet.InitServlet;
import com.table.borde.vo.PostVO;

public class PostDAOImpl implements PostDAO {

	@Override
	public int insertPost(PostVO post) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into post_list(post_no, post_title, post_content , post_crename, post_credat, post_location, post_admin)"
				+ " values(seq_post_list_no.nextval,?,?,?,sysdate,?,?)";
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getPostTitle());
			ps.setString(2, post.getPostContent());
			ps.setString(3, post.getPostCrename());
			ps.setInt(4, post.getPostLocation());
			ps.setInt(5, post.getPostAdmin());
			int result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public int updatePost(PostVO post) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update post_list set post_title=?, post_content=? ,"
				+ " post_update_name=?, post_update_dat=sysdate , post_update_cnt=? , post_location=? "
				+ " where post_no=?";
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getPostTitle());
			ps.setString(2, post.getPostContent());
			ps.setString(3, post.getPostUpName());
			ps.setInt(4, post.getPostUpdateCnt());
			ps.setInt(5, post.getPostLocation());
			ps.setInt(6, post.getPostNo());
			int result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public int deletePost(int postNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from post_list where post_no=?";
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postNo);
			int result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public PostVO selectPost(int postNo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from post_list where post_no =?";
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				PostVO pvo = new PostVO();
				pvo.setPostNo(rs.getInt("post_no"));
				pvo.setPostTitle(rs.getString("post_title"));
				pvo.setPostContent(rs.getString("post_content"));
				pvo.setPostCrename(rs.getString("post_crename"));
				pvo.setPostCredat(rs.getString("post_credat"));
				pvo.setPostUpName(rs.getString("post_update_name"));
				pvo.setPostUpdateDat(rs.getString("post_update_dat"));
				pvo.setPostUpdateCnt(rs.getInt("post_update_cnt"));
				pvo.setPostLocation(rs.getInt("post_location"));
				pvo.setPostAdmin(rs.getInt("post_admin"));
				return pvo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public List<PostVO> selectPostList(List<PostVO> post) {
		List<PostVO> pList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from post_list order by post_no desc";
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				PostVO pvo = new PostVO();
				pvo.setPostNo(rs.getInt("post_no"));
				pvo.setPostTitle(rs.getString("post_title"));
				pvo.setPostContent(rs.getString("post_content"));
				pvo.setPostCrename(rs.getString("post_crename"));
				pvo.setPostCredat(rs.getString("post_credat"));
				pvo.setPostUpName(rs.getString("post_update_name"));
				pvo.setPostUpdateDat(rs.getString("post_update_dat"));
				pvo.setPostUpdateCnt(rs.getInt("post_update_cnt"));
				pvo.setPostLocation(rs.getInt("post_location"));
				pvo.setPostAdmin(rs.getInt("post_admin"));
				pList.add(pvo);
			}
			return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}

}
