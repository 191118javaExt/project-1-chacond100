package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.Login;
import com.revature.models.User;

@MultipartConfig
public class LoginServlet extends HttpServlet{
	
		private static final long serialVersionUID = 1L;
		private static Logger logger= Logger.getLogger(LoginServlet.class);
		Application application = new Application();
		private static ObjectMapper om = new ObjectMapper();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			try {
				doPost(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
			BufferedReader reader = req.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = reader.readLine();
			while(line!=null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
			String body = stringBuilder.toString();
			Login login= om.readValue(body, Login.class);
			String username = login.getUsername();
			String password = login.getPassword();
			//String password = DigestUtils.sha256Hex(password1);
			logger.info("Login attempt from username:"+username);
			
		try {	
			
			User currentUser = application.login(username, password);
			logger.info(currentUser);
			if((currentUser != null ) && (currentUser.getRole_ID() == 1)){
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("user_ID", currentUser.getUser_ID());
				
				PrintWriter outputStream = res.getWriter();
				res.setContentType("application/json");
				res.setStatus(418);
				outputStream.println(om.writeValueAsString(currentUser));
				logger.info(username+" succesfully logged in as a financial manager");
			}else if ((currentUser != null) && (currentUser.getRole_ID() == 2)){
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("user_ID", currentUser.getUser_ID());
				
				PrintWriter outputStream = res.getWriter();
				res.setContentType("application/json");
				res.setStatus(200);
				outputStream.println(om.writeValueAsString(currentUser));
				logger.info(username+" succesfully logged in as an employee");
			}else {
				logger.warn("Failed to login with username:"+username);
				res.setContentType("application/json");
				res.setStatus(204);
			}
	}catch(NullPointerException e) {
		logger.info("failed login with username: " + username);
		res.setContentType("application/json");
		res.setStatus(204);
		}
	}
}
	
