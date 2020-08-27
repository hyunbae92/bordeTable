package com.table.borde.service;

import javax.servlet.http.HttpSession;

import com.table.borde.vo.UserInfoVO;

public interface UserInfoService {

	int doSignIn(UserInfoVO user, HttpSession session);
}
