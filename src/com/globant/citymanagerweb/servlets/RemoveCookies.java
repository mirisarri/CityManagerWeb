package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveCookies
 */
@WebServlet("/removecookies.do")
public class RemoveCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCookies() {
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
		
		response.setContentType("text/html");
		Cookie[] myCookies = request.getCookies();
		if(myCookies != null && myCookies.length > 0) {
			for(Cookie c : myCookies) {
				if(c.getName().toLowerCase().contains("credentials")) {
					//expire the cookie
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><h3>Cookies expired</h3></body></html>");
	}

}
