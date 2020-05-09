package com.test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("html/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting the parameters
		String username  = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		// validating user from the database.
		ApplicationDao dao = new ApplicationDao();
		int ans = dao.loginUser(username, password);
		
		if(ans == 2) {
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			request.setAttribute("message","Login successful");
			request.getRequestDispatcher("profile").forward(request, response);
		}
		else if(ans == 1) {
			request.setAttribute("message","Username does not exist");
			request.getRequestDispatcher("html/login.jsp").forward(request, response);
		}
		else if(ans == 3) {
			request.setAttribute("message","Password do not match");
			request.getRequestDispatcher("html/login.jsp").forward(request, response);
		}
		else {
			request.setAttribute("message","Some error occured");
			request.getRequestDispatcher("html/login.jsp").forward(request, response);
		}
	}

}
