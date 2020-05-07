package com.test.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Transaction;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class addTransaction
 */
@WebServlet("/addTransaction")
public class addTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationDao dao = new ApplicationDao();
		String groupId = request.getParameter("groupId");
		System.out.print(groupId+"hiii");
		ArrayList<String> members = dao.getGroupMembers(groupId);
		request.setAttribute("groupId",groupId);;
		request.setAttribute("members",members);
		for(String i: members) {
			System.out.println(i);
		}
		request.setAttribute("groupId",groupId);
		request.getRequestDispatcher("html/addExpense.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated me

		ApplicationDao dao = new ApplicationDao();
		String name = request.getParameter("transactionName");
		String Description = request.getParameter("transactionDescription");
		double Money = Double.parseDouble(request.getParameter("transactionMoney"));
		System.out.print(Money);
		String[] getSelectedMembers = new String[100];
		String groupId =(String) request.getParameter("groupId");
		System.out.print("helloasdf"+groupId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		String transDate = formatter.format(date).toString();
		String transId = "transId"+transDate.hashCode();
		System.out.println(transId+" "+name+" "+Description+" "+Money+" "+groupId);
		transId.replace("-","");
		String username = (java.lang.String) request.getSession().getAttribute("username");
		
		getSelectedMembers = request.getParameterValues("selected");
		for(String i: getSelectedMembers) {
			System.out.print(i);
		}
		ArrayList<String> transactionMembers = new ArrayList<String>();
		if(getSelectedMembers == null) {
			
			request.setAttribute("message","Please select the split!");
			doGet(request,response);
		}
		else if(getSelectedMembers[0].equals("Select All")) {
			transactionMembers = dao.getGroupMembers(groupId);
		}
		else {
			for(String i: getSelectedMembers) {
				System.out.print(i);
				transactionMembers.add(i);
			}
			transactionMembers.add(username);
		}
		System.out.println(groupId);
		Transaction transaction = new Transaction(name,Description,groupId,transId,transDate,transactionMembers,Money,username);
		int ans = dao.addTransactionToDatabase(transaction);
		if(ans == 0) {
			request.setAttribute("message","There is some error. Please try again");
			request.getRequestDispatcher("profile").forward(request, response);
		}
		else {
			request.setAttribute("groupId",groupId);
			request.setAttribute("message","Expense Added succeffully!. You can see it in the logs below");
			request.getRequestDispatcher("groupDetails").forward(request, response);
		}
	}
}
