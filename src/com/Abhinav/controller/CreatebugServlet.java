package com.Abhinav.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Abhinav.dao.BugDao;
import com.Abhinav.model.Bug;


@WebServlet("/create")
public class CreatebugServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("addbugservlet");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if(request.getSession().getAttribute("username")==null){
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		int id=Integer.parseInt(request.getParameter("id"));
		String bugname=request.getParameter("bugname");
		String bugdetails=request.getParameter("bugdetails");
		String employee=request.getParameter("employee");
		if(id==0 || bugname.equals("") || bugdetails.equals("") || employee.equals(""))
			{request.getRequestDispatcher("Addbugdetails.jsp").forward(request, response);return;}
		BugDao bugdao= new BugDao();
		bugdao.addbug(id,bugname,bugdetails,employee);
		ArrayList<Bug> al=bugdao.show();
		//request.setAttribute("name","Abhinav");
		request.setAttribute("list",al);
		request.getRequestDispatcher("Bugs.jsp").forward(request, response);
	}

}
