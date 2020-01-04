package com.revature.web;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.Reimbursement;
import java.io.PrintWriter;

public class DisplayServlet extends HttpServlet{
	
	private static final long serialVersionUID = -8530760844302372684L;
	private static Logger logger = Logger.getLogger(DisplayServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	Application application = new Application();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		HttpSession session = req.getSession();
		
		try {
			int user_ID = (int) session.getAttribute("user_ID");
			List<Reimbursement> list = Application.getReimbursementByID(user_ID);
				if(list.isEmpty()) {
					logger.warn("User does not have any reimbursements");	
					res.setContentType("application/json");
					res.setStatus(204);
				}else {
					PrintWriter out = res.getWriter();
					res.setContentType("application/json");
					res.setStatus(200);
					out.println(om.writeValueAsString(list));
					}
			}catch(NumberFormatException e) {	
				logger.warn("Failed to display reimbursements");
				res.setContentType("application/json");
				res.setStatus(204);
				}
		}
}
		
	
