package com.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.CategoryDAO;
import com.blog.dao.PostDAO;
import com.blog.dto.CategoryDTO;
import com.blog.dto.PostDTO;

public class BoardController extends HttpServlet {
	Logger log = Logger.getLogger("com.blog.controller.BoardController");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PostDAO postDAO = new PostDAO();
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out=resp.getWriter();
		// model
			// 글 전체 리스트 
		try {
			out.println("{\"post\":[");
			List<PostDTO> list = postDAO.getAllList();
			for(int i=0; i<list.size(); i++) {
				PostDTO bean=list.get(i);
				if(i!=0)out.print(",");
				out.println("{\"post_num\":" + bean.getPostNum() 
						+ ", \"subject\":\"" + bean.getSubject() 
						+ "\", \"category\":\"" + bean.getCategory() 
						+ "\", \"post_date\":\"" + bean.getPostDate() + "\"}");
			}
			out.println("]}");
			
//			req.setAttribute("postList", list);			
//			log.info("info : "+ list);			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(out!=null)out.close();
		}
		
		// view resolver
//		RequestDispatcher rd;
//		rd = req.getRequestDispatcher("list.jsp");
//		rd.forward(req, resp);
	}
}
