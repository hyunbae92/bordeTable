package com.table.borde.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.table.borde.service.UserInfoService;
import com.table.borde.service.impl.UserInfoServiceImpl;
import com.table.borde.vo.UserInfoVO;

@WebServlet("/ajax/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private UserInfoService userInfoService = new UserInfoServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader br=  request.getReader();
		String str = "";
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		System.out.println(sb.toString());
		Map<String,Object> rMap = new HashMap<>();
		UserInfoVO user = gson.fromJson(sb.toString(), UserInfoVO.class);
		if("signin".equals(user.getCmd())) {
			HttpSession session = request.getSession();
			rMap.put("result", userInfoService.doSignIn(user, session));
			System.out.println(rMap.get("result"));
		}
		
		pw.write(gson.toJson(rMap));
	}

}
