package com.blog.controller;

import java.io.IOException;
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

public class NewPostController extends HttpServlet {
	Logger log = Logger.getLogger("com.blog.controller.EditController");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		CategoryDAO categoryDAO = new CategoryDAO();
		
		try {
			List<CategoryDTO> categoryList = categoryDAO.getList();
			req.setAttribute("categoryList", categoryList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		RequestDispatcher rd = req.getRequestDispatcher("add.html");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String subject = req.getParameter("subject").trim();
		String category = req.getParameter("category").trim();
		String content= req.getParameter("content").trim();

		PostDAO postDAO = new PostDAO();
		try {
			if (postDAO.insertPost(subject, category, content) > 0) {
				resp.setStatus(201);
			} else {
				resp.setStatus(400);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
			
	}
}
