package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginCookies
 */
@WebServlet("/userlogincookies.do")
public class UserLoginCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginCookies() {
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
		
		//pull the values from the post variables
		String uid = request.getParameter("userID");
		String pwd = request.getParameter("pwd");
		boolean remember = false;
		if(request.getParameter("remember") != null) {
			String rememberMe = request.getParameter("remember");
			if(rememberMe.equalsIgnoreCase("on")) {
				remember = true;
			}
		}
		
		//here there should be some logic to validate the user...
		int authLevel = 1;
		
		//to start a session, get the session from the request into a variable
		HttpSession s = request.getSession();
		s.setAttribute("userName", uid);
		s.setAttribute("userAuthLevel", authLevel);
		
		if(remember) {
			//store information on a cookie for later use
			int cookieLife = 3600*24*7; //7 days
			
			Cookie uidCookie = new Cookie("credentials_uid", uid);
			uidCookie.setMaxAge(cookieLife);
			response.addCookie(uidCookie);
			
			Cookie pwdCookie = new Cookie("credentials_pwd", pwd);
			pwdCookie.setMaxAge(cookieLife);
			response.addCookie(pwdCookie);
		}
		
		//redirect
		if(authLevel < 1 || uid == null || uid == "") {
			response.sendRedirect(response.encodeRedirectURL("index_cookies.html"));
		}
		else {
			response.sendRedirect(response.encodeRedirectURL("/CityManagerWeb/destinationpage.do"));
		}
	}

}
