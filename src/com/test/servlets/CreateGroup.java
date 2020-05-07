package com.test.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Group;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/createGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("html/createGroup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		   Date date = new Date();
		//getting the parameters
		String groupName = request.getParameter("name");
		String description = request.getParameter("description");
		String groupCreatedDate = formatter.format(date).toString();
		String groupId = Integer.toString(groupCreatedDate.hashCode());
		String groupOwner = (String) request.getSession().getAttribute("username");
		//forming the group parameters.
		//forming the object.
		Group group = new Group(groupName,groupId,description,groupCreatedDate,groupOwner);
		//setting up connection with the database.
		ApplicationDao dao = new ApplicationDao();
		int ans = dao.createGroup(group,(String)request.getSession().getAttribute("username"));
		if(ans == 0) {
			request.setAttribute("message","some error occured while creating the group");
		}
		else {
			String message = "Group created successfully! GIve you friends groupId: "+groupId+" so that they can join";
			request.setAttribute("message",message);
		}
		request.getRequestDispatcher("profile").forward(request, response);
	}

}
