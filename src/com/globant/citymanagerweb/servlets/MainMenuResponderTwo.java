package com.globant.citymanagerweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponderTwo
 */
@WebServlet("/mainmenurespondertwo.do")
public class MainMenuResponderTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuResponderTwo() {
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
		
		//get the form parameter that was posted
		String userChoice = request.getParameter("menuChoice");
		String[] userOptions = request.getParameterValues("adminoptions");
		
		request.setAttribute("userChoice", userChoice);
		if(userOptions != null) {
			
			for(int i=0; i<userOptions.length; i++) {
				
				if(userOptions[i].equalsIgnoreCase("useDB")) {
					request.setAttribute("useDB", "1");
				}
				if(userOptions[i].equalsIgnoreCase("sendEmail")) {
					request.setAttribute("sendEmail", "1");
				}
			}
		}
		
		//HTML files can't read attributes so we will first send to an intermediary servlet
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainmenuresponderhelper.do");
		dispatcher.forward(request, response);
	}

}
