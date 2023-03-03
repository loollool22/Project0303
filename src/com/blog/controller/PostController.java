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

import com.blog.dao.CommentDAO;
import com.blog.dao.PostDAO;
import com.blog.dto.CommentDTO;
import com.blog.dto.PostDTO;

public class PostController extends HttpServlet {
	Logger log = Logger.getLogger("com.blog.controller.PostController");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 매개변수로 쓸 게시글 번호를 가져옴
		resp.setContentType("application/json; charset=utf-8");
		int postNum = Integer.parseInt(req.getParameter("postNum"));
//		System.out.println("one param:"+postNum);
		PostDAO postDAO = new PostDAO();
		PostDTO postBean;
		CommentDAO commentDAO = new CommentDAO();
		CommentDTO commentBean;
		PrintWriter out = resp.getWriter();
		
		try {
			postBean = postDAO.getPost(postNum);
			out.println("{\"post\":[");
			out.println("{\"post_num\":" + postBean.getPostNum()
					+ ", \"read_count\":" + postBean.getReadCount() 
					+ ", \"subject\":\"" + postBean.getSubject() 
					+ "\", \"category\":\"" + postBean.getCategory() 
					+ "\", \"post_content\":\"" + postBean.getPostContent() 
					+ "\", \"post_date\":\"" + postBean.getPostDate() + "\"}");
			out.println("]}");
//			out.println(", ");
//			out.println("{\"comment\":[");
//			List<CommentDTO> commentList = commentDAO.getPostCommentList(postNum);
//			for(int i=0; i< commentList.size(); i++) {
//				commentBean = commentList.get(i);
//				if(i!=0)out.print(",");
//				out.println("{\"comment_num\":" + commentBean.getCommentNum()
//						+ ", \"post_num\":" + commentBean.getPostNum() 
//						+ ", \"comment_parent_num\":" + commentBean.getCommentParentNum() 
//						+ ", \"nickname\":\"" + commentBean.getNickname() 
//						+ "\", \"comment_content\":\"" + commentBean.getCommentContent()
//						+ "\", \"comment_date\":\"" + commentBean.getCommentDate() + "\"}");
//			}
//			out.println("]}");
			
//			req.setAttribute("postDTO", postBean);
//			req.setAttribute("commentList", commentList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(out!=null)out.close();
		}
	
//		RequestDispatcher rd = req.getRequestDispatcher("post.html");
//		rd.forward(req, resp);
	}
}
