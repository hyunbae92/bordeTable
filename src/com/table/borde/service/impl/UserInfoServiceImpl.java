package com.table.borde.service.impl;

import javax.servlet.http.HttpSession;

import com.table.borde.dao.UserInfoDAO;
import com.table.borde.dao.impl.UserInfoDAOImpl;
import com.table.borde.service.UserInfoService;
import com.table.borde.vo.UserInfoVO;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
	@Override
	public int doSignIn(UserInfoVO user, HttpSession session) {
		user = userInfoDAO.signIn(user);
		if(user!=null) {
			session.setAttribute("user", user);
			return 1;
		}
		return 0;
	}

}
