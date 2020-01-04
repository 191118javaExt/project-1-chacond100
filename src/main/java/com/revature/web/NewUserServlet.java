package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.ersapplication.Application;
import com.revature.models.UserEntry;

@MultipartConfig
public class NewUserServlet extends HttpServlet {

	private static final long serialVersionUID = -3880623841323631968L;
		private static Logger logger = Logger.getLogger(NewUserServlet.class);
		Application application = new Application();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			try {
				doPost(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				}
			}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			
			String firstName = req.getParameter("FirstName");
			String lastName = req.getParameter("LastName");
			String username = req.getParameter("Username");
			String password = req.getParameter("Password");
			String email = req.getParameter("Email");
			int role_ID = Integer.parseInt(req.getParameter("Role_ID"));
			
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(username);
			System.out.println(password);
			System.out.println(email);
			System.out.println(role_ID);
			
			UserEntry entry = new UserEntry(username, password, firstName, lastName, email, role_ID);
			
			
			
			boolean newUser = application.addUser(entry);
			
			if(newUser) {
				res.setStatus(201);
				logger.info("Succesfully submitted new user form");
				res.sendRedirect("/project-1/HTML/Project-1-Login.html");
			}
			
		}
	}

