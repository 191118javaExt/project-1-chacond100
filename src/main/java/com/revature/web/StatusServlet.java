package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.ReimbursementUpdate;

@MultipartConfig
public class StatusServlet extends HttpServlet {

		private static final long serialVersionUID = -6557631464197154247L;
		private static Logger logger = Logger.getLogger(StatusServlet.class);
		Application application = new Application();
		private static ObjectMapper om = new ObjectMapper();
		
		
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
				HttpSession session = req.getSession();
				int user_ID = (int) session.getAttribute("user_ID");
				logger.info("Author ID: "+ user_ID);
				BufferedReader reader = req.getReader();
				StringBuilder jsonInput = new StringBuilder();
				String line = reader.readLine();
				while(line != null) {
					jsonInput.append(line);
					line = reader.readLine();
				}
				String jsonInputString = jsonInput.toString();
				ReimbursementUpdate reimbursementUpdate = om.readValue(jsonInputString, ReimbursementUpdate.class);
				int reimb_ID = reimbursementUpdate.getReimb_ID();
				logger.info("Reimbursement ID :"+reimb_ID);
				int status_ID = reimbursementUpdate.getStatus_ID();
				logger.info("Status ID :"+status_ID);
				if(application.updateReimbursement(reimb_ID, status_ID, user_ID)) {
					res.setStatus(200);
				}	
			}
		}
					
