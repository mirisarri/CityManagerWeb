package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/userlogin.do")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//here there should be some logic to validate the user...
		String uid = "authorized_user";
		int authLevel = 1;
		
		//assuming the user is valid let's set some information into the session
		//to start a session, get the session from the request into a variable
		HttpSession s = request.getSession();
		s.setAttribute("userName", uid);
		s.setAttribute("userAuthLevel", authLevel);
		
		//redirect
		if(authLevel < 1 || uid == null || uid == "") {
			//send back to calling page or forward to unauthorized notification
			response.sendRedirect(response.encodeRedirectURL("Login.html"));
		}
		else {
			//forward to requested page, or menu page with authorization
			response.sendRedirect(response.encodeRedirectURL("/CityManagerWeb/destinationpage.do"));
		}
	}

}
