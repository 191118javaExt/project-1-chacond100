package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ersapplication.Application;
import com.revature.models.Reimbursement;

public class DisplayAllServlet extends HttpServlet{
	private static final long serialVersionUID = -2223879314393934697L;
	//private static Logger logger = Logger.getLogger(DisplayServlet.class);
	private static ObjectMapper om = new ObjectMapper();
	Application application = new Application();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		try {
			doGet(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		PrintWriter outputStream = res.getWriter();
		res.setContentType("application/json");
		res.setStatus(200);
		TreeMap<Integer, Reimbursement> allReimbursements = application.grabAllReimbursements();
		outputStream.println(om.writeValueAsString(allReimbursements));
	}
}
