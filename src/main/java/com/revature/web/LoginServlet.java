package com.revature.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.ersapplication.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LoginServlet extends HttpServlet{
	
		private static final long serialVersionUID = 1L;
		private static Logger logger= Logger.getLogger(LoginServlet.class);
		Application system = new Application();
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String verificationInput = username+password;
			
			logger.info("Login attemp from username: " +username);
			int user_ID;
			String verification = system.login(user_ID);
			
			if(verification.equals(verificationInput)) {
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				RequestDispatcher rd = req.getRequestDispatcher("employee/project1-employee.html");
				rd.forward(req, res);
				logger.info("Succesfully logged in");
			}else {
				logger.warn("Failed to login");
			}
		}
}
