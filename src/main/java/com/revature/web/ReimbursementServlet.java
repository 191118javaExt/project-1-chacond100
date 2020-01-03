package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
//import org.omg.CORBA.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementEntry;

@MultipartConfig
public class ReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReimbursementServlet.class);
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
		
		double amount = Double.parseDouble(req.getParameter("Amount"));
		System.out.println(amount);
		int type_ID = Integer.parseInt(req.getParameter("Type_ID"));
		
		String description = req.getParameter("Description");
		
		ReimbursementEntry input = new ReimbursementEntry(amount, description, type_ID);
		
		boolean newReimb = application.addReimbursement(input, user_ID);
		
		if(newReimb) {
			res.setStatus(201);
			res.sendRedirect("/project-1/HTML/Project-1-EmpReimbView.html");
		}
		
	}
}
