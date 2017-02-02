package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeRequest
 */
@WebServlet("/ThreadSafeRequest")
public class ThreadSafeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadSafeRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the attribute and multiply by two to increase factor
		//but pretend this is in a longer running process
		StringBuilder msg = new StringBuilder("");
		msg.append("<html><body>");
		int currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute retrieved value: %d<br>", currentSeed));
		currentSeed *= 2;
		
		//set the value in the request
		request.setAttribute("currentSeedValue", currentSeed);
		
		//set the value in the context
		getServletContext().setAttribute("currentSeedValue", currentSeed);
		
		try {
			Thread.sleep(5000); //wait 5 seconds
		}
		catch(InterruptedException e) {
			//do nothing
		}
		
		//re-fetch the value from the request after waiting 
		currentSeed = (int) request.getAttribute("currentSeedValue");
		msg.append(String.format("Request attribute retrieved value after process: %d<br>", currentSeed));
		
		//and from the context
		currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Context attribute retrieved value after process: %d", currentSeed));
		
		msg.append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
