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

import com.google.gson.Gson;
import com.table.borde.service.PostService;
import com.table.borde.service.impl.PostServiceImpl;
import com.table.borde.vo.PostVO;

@WebServlet("/ajax/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private PostService postService = new PostServiceImpl();
			
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		PrintWriter pw = response.getWriter();
		Map<String,Object> result = new HashMap<>();
		if("list".equals(cmd)) {
			result.put("result", postService.doSelectPostList(null));
		}else if("post".equals(cmd)) {
			result.put("result", postService.doSelectPost(Integer.parseInt(request.getParameter("postNo"))));
		}
		
		pw.write(gson.toJson(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String str = "";
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		Map<String,Object> result = new HashMap<>();
		PostVO postVO = gson.fromJson(sb.toString(), PostVO.class);
		if("insert".equals(postVO.getCmd())) {
			result.put("result", postService.doInsertPost(postVO));
		}else if("update".equals(postVO.getCmd())) {
			result.put("result", postService.doUpdatePost(postVO));
		}else if("delete".equals(postVO.getCmd())) {
			result.put("result", postService.doDeletePost(postVO.getPostNo()));
		}
		pw.write(gson.toJson(result));
		
	}

}
