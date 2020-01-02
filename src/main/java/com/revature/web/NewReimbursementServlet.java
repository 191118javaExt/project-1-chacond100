package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.Login;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementEntry;
import com.revature.models.ReimbursementType;
import com.revature.models.User;

public class NewReimbursementServlet {
			
	private static Logger logger= Logger.getLogger(LoginServlet.class);
	Application application = new Application();
	private static ObjectMapper om = new ObjectMapper();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		try {
			doPost(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			}
		}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		int user_ID = (int)session.getAttribute("user_ID");
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while(line!=null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = stringBuilder.toString();
		ReimbursementEntry reimbursementEntry= om.readValue(body, ReimbursementEntry.class);
		//double amount = reimbursementEntry.getAmount();
		//String description = reimbursementEntry.getDescription();
		//int type_ID = reimbursementEntry.getType_ID();
		//int author = reimbursementEntry.getAuthor();
	
		boolean reimbursement = application.addReimbursement(reimbursementEntry, user_ID);
		System.out.println(reimbursement);
		if(reimbursement){
			
			res.setStatus(201);
		}else {
			logger.warn("Failed to succesfully submitt the reimbursement form");
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}
}
