package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Group;
import com.test.beans.Transaction;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class groupDetails
 */
@WebServlet("/groupDetails")
public class groupDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public groupDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession(false) == null) {
			request.setAttribute("message","Login or register first");
			request.getRequestDispatcher("login").forward(request, response);
		}
		System.out.print(request.getSession().getAttribute("username")+"holla");
		
		String groupId = request.getParameter("groupId");
		ApplicationDao dao = new ApplicationDao();
		/**
		 * TODO: 1. Get group details.
		 * TODO: 2. Get member details
		 * TODO 3. Get transaction history.
		 */
		Group group = dao.getGroupDetails(groupId);
		ArrayList<String> members = dao.getGroupMembers(groupId);
		ArrayList<Transaction> transactions = dao.getTransactionDetails(groupId);
		for(Transaction t: transactions) {
			System.out.println(t.getTransactionId());
		}
		request.setAttribute("groupDetails",group);
		request.setAttribute("members",members);
		request.setAttribute("groupOwner",group.getGroupOwner());
		request.setAttribute("transactions",transactions);
		request.setAttribute("groupId",groupId);
		request.getRequestDispatcher("html/groupDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
