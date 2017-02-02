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
 * Servlet implementation class LoginWithCookieInfo
 */
@WebServlet("/loginwithcookieinfo.do")
public class LoginWithCookieInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginWithCookieInfo() {
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

		String uid = "";
		String pwd = "";
		
		//attempt to pull information from a cookie
		Cookie[] myCookies = request.getCookies();
		if(myCookies != null) {
			for(Cookie c: myCookies) {
				if(c.getName().equalsIgnoreCase("credentials_uid")) {
					uid = c.getValue();
				}
				else if(c.getName().equalsIgnoreCase("credentials_pwd")) {
					pwd = c.getValue();
				}
			}
		}
		
		//build the page
		StringBuilder sb = new StringBuilder("");
		sb.append("<html><body>");
		sb.append("<form id='frmLogin' name='frmLogin' action='/CityManagerWeb/userlogincookies.do' method='post'>");
		sb.append("<table>");
		sb.append("<tr><td><span>Username:</span></td><td><input type='text' name='userID' value='" + uid + "' /></td></tr>");
		sb.append("<tr><td><span>Password:</span></td><td><input type='password' name='pwd' value='" + pwd + "' /></td></tr>");
		sb.append("<tr><td colspan='2' align='right'><input type='checkbox' name='remember' />Remember me</td></tr>");
		sb.append("<tr><td colspan='2' align='right'><input type='submit' name='loginButton' value='Log in' /></td></tr>");
		sb.append("</table>");
		sb.append("</form>");
		sb.append("</body></html>");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
	}

}
