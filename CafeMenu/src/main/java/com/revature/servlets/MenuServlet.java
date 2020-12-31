package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.MenuItem;

@WebServlet("/master")
public class MenuServlet extends HttpServlet {
	
	public static List<MenuItem> menu = new ArrayList<MenuItem>();
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		resp.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		
		if(req.getParameter("all").equals("true")) {
			resp.getWriter().write(om.writeValueAsString(menu));
		} else {
			MenuItem item = null;
			String name = req.getParameter("name");
			
			for(MenuItem i: menu) {
				if(i.getName().equals(name)) {
					item = i;
				}
			}
			
			PrintWriter pw = resp.getWriter();
			
			if(item != null) {
				resp.getWriter().write(om.writeValueAsString(item));
			} else {
				resp.setStatus(400);
				pw.write("Item not found");
			}
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{		
		boolean add = true;
		
		ObjectMapper om = new ObjectMapper();
		
		MenuItem item = om.readValue(req.getReader(), com.revature.models.MenuItem.class);
		
		for(MenuItem i: menu) {
			if(i.getName().equals(item.getName())) {
				add = false;
			}
		}
		
		PrintWriter pw = resp.getWriter();
		
		if(add) {
			menu.add(item);
			pw.write("Item added");
		} else {
			resp.setStatus(400);
			pw.write("Failed to add item");
		}
	}
	
	@Override 
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		MenuItem item = null;
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		
		for(MenuItem i: menu) {
			if(i.getName().equals(name)) {
				i.setPrice(price);
				item = i;
			}
		}
		
		PrintWriter pw = resp.getWriter();
		
		if(item != null) {
			pw.write("Item updated");
		} else {
			resp.setStatus(400);
			pw.write("Item not found");
		}
	}
	
	@Override 
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		MenuItem item = null;
		String name = req.getParameter("name");
		
		for(MenuItem i: menu) {
			if(i.getName().equals(name)) {
				item = i;
			}
		}
		
		PrintWriter pw = resp.getWriter();
		
		if(item != null) {
			menu.remove(item);
			pw.write("Item removed");
		} else {
			resp.setStatus(400);
			pw.write("Item not found");
		}
	}
}
