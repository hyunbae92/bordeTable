package com.table.borde.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.table.borde.dao.UserInfoDAO;
import com.table.borde.servlet.InitServlet;
import com.table.borde.vo.UserInfoVO;

public class UserInfoDAOImpl implements UserInfoDAO {

	@Override
	public UserInfoVO signIn(UserInfoVO user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user_info where ui_id=? and ui_password =?";
		
		try {
			conn = InitServlet.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUiId());
			ps.setString(2, user.getUiPassword());
			rs = ps.executeQuery();
			if(rs.next()){
				user.setUiName(rs.getString("ui_name"));
				user.setUiAdmin(rs.getString("ui_admin"));
				return user; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}
	public static void main(String[] args) {
		InitServlet is = new InitServlet();
		is.init();
		UserInfoVO user = new UserInfoVO();
		user.setUiId("test");
		user.setUiPassword("1234");
		UserInfoDAO ui = new UserInfoDAOImpl();
		System.out.println(ui.signIn(user));
	}
}
