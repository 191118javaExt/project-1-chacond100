package com.revature.web;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.ReimbursementEntry;

@MultipartConfig
public class ReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReimbursementServlet.class);
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
		
		HttpSession session = req.getSession();
		
		int user_ID = (int) session.getAttribute("user_ID");
		
		double amount = Double.parseDouble(req.getParameter("Amount"));
		
		int type_ID = Integer.parseInt(req.getParameter("Type_ID"));
		
		String description = req.getParameter("Description");
		
		ReimbursementEntry input = new ReimbursementEntry(amount, description, type_ID);
		
		boolean newReimb = application.addReimbursement(input, user_ID);
		
		if(newReimb) {
			res.setStatus(201);
			logger.info("Succesfully submitted new reimbursement form");
			res.sendRedirect("/project-1-chacond100/HTML/Project-1-EmpReimbView.html");
		}
		
	}
}
