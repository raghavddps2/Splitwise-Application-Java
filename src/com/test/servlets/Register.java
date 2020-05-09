package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.beans.*;
import com.test.dao.ApplicationDao;
/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("html/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//getting parameters
		String name = (String) request.getParameter("name");
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String mobile = (String) request.getParameter("mobile");
		String confirmPassword = (String) request.getParameter("confirm_password");
		System.out.println(name+username+password+email+confirmPassword);
		//making an object.
		User user = new User(name,email,username,mobile,password);
		
		if(!password.equals(confirmPassword)) {
			request.setAttribute("message","Password and confirm password fields do not match");
			request.getRequestDispatcher("html/register.jsp").forward(request,response);
		}
		else {
			//getting connection to database.
			
			ApplicationDao dao = new ApplicationDao();
			int result = dao.RegisterUser(user);
			
			if(result == 0) {
				request.setAttribute("message","Username already used. Please choose another username");
			}
			else {
				
				int ans = dao.createUserGroupTable(username);
				if(ans == 0) {
					request.setAttribute("message","Some error occured");
				}
				else {
					request.setAttribute("message","User registered successfully. You can login now.");
				}
			}
			request.getRequestDispatcher("html/register.jsp").forward(request, response);
			
		}
	}

}
