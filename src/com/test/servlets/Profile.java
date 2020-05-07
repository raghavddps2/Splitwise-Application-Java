package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Group;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hiiiii");
		String message = (String)request.getAttribute("message");
		request.setAttribute("message",message);
		String username = (String) request.getSession().getAttribute("username");
		HashMap<String,ArrayList<Group>> groups = new HashMap<String,ArrayList<Group>>();
		
		//contact db
		ApplicationDao dao = new ApplicationDao();
		groups = (HashMap<String, ArrayList<Group>>) dao.getMadeAndJoinedGroups(username);
		
		ArrayList<Group> user_made_grp = groups.get("groups_made_by_user"); 
		ArrayList<Group> user_joined_grp = groups.get("groups_joined_by_user");
		request.setAttribute("user_made_grp",user_made_grp);
		request.setAttribute("user_joined_grp",user_joined_grp);
//		Iterator iterator = user_made_grp.iterator();
//		while(iterator.hasNext()) {
//			Group grp = (Group) iterator.next();
//			System.out.println(grp.getGroupId());
//		}
//		Iterator iterator2 = user_made_grp.iterator();
//		while(iterator2.hasNext()) {
//			Group grp = (Group) iterator2.next();
//			System.out.println(grp.getGroupId());
//		}
		request.getRequestDispatcher("html/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
