package com.test.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.test.beans.Group;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class joinGroup
 */
@WebServlet("/joinGroup")
public class joinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("html/joinGroup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		String groupId = request.getParameter("groupId");
		String username= (String) request.getSession().getAttribute("username");
		System.out.print("hello");
		//Using the models
		ApplicationDao dao = new ApplicationDao();
		Map<String, Map<Integer, Group>> map = new HashMap<String,Map<Integer,Group>>();
		map = dao.getGroupDetails(groupId, username);
		Map<Integer, Group> ans = map.get("ans");
		Entry<Integer, Group> entry = ans.entrySet().iterator().next();
		 Integer ans1 = entry.getKey();
		 Group group = entry.getValue();
		if(ans1 == 0) {
			request.setAttribute("message","Some error occured");
			request.getRequestDispatcher("html/joinGroup.jsp").forward(request,response);
		}
		else if(ans1 == 1) {
			request.setAttribute("message","Invalid Group Id");
			request.getRequestDispatcher("html/joinGroup.jsp").forward(request, response);
		}
		else if(ans1 == 2){
			ans1 = dao.joinGroup(groupId,username,group);
			if(ans1 == 0) {
				request.setAttribute("message","Some error occured");
				request.getRequestDispatcher("html/joinGroup.jsp").forward(request,response);
			}
			else {
				request.setAttribute("message","You are added to the group, It will be visible on your dashboard now");
				request.getRequestDispatcher("html/profile.jsp").forward(request,response);
			}
		}
	}

}
