package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.citymanagerweb.sessionlisteners.ActiveUserTracking;

/**
 * Servlet implementation class ShowActiveSessionCount
 */
@WebServlet("/showactivesessioncount.do")
public class ShowActiveSessionCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowActiveSessionCount() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Just print the active user count
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		int activeUsers = ActiveUserTracking.getActiveUsersCount();
		out.println("<h1>Active users: " + activeUsers + "</h1>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
