package com.globant.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globant.citymanagerweb.helpers.DBWorldQueries;
import com.globant.citymanagerweb.models.DBManager;
import com.globant.citymanagerweb.models.MySQLServerConnectionBehavior;
import com.globant.citymanagerweb.models.ServerConnectionBehavior;

/**
 * Servlet implementation class ListAllCities
 */
@WebServlet("/listallcities.do")
public class ListAllCities extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllCities() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the DBManager from context
		DBManager dbm = (DBManager) getServletContext().getAttribute("WorldDBManager");
		
		//generate the output in a StringBuilder
		StringBuilder sb = new StringBuilder("<html><body>");
		/*
		//get db conn info from context init params
		String uid = getServletContext().getInitParameter("dbuserid");
		String pwd = getServletContext().getInitParameter("dbuserpwd");
		String cat = getServletContext().getInitParameter("dbinitcat");
		
		//set the scb for mySQL
		ServerConnectionBehavior scb = new MySQLServerConnectionBehavior(uid, pwd, cat);
		System.out.println(scb.getConnectionDetails());
		System.out.println(scb.getConnectionUrl());
		
		//create the manager
		DBManager dbm = new DBManager(scb);
		*/
		try {
			
			//connect to the db and open the connection
			if(!dbm.isConnected()) {
				if(!dbm.openConnection()) {
					//massive failure, log it
					sb.append("Could not connect to the database...");
				}
			}
			
			//ID Name CountryCode District Population
			//get the cities into a table
			sb.append("<table border='1'>"
					+ "<tr><td>ID</td><td>Name</td><td>Country code</td><td>District</td><td>Population</td></tr>");
			
			// Not MCV, queries should be logic outside of the controller
			//String query = "select * from city where CountryCode = 'USA' order by District ASC, Population DESC";
			String query = DBWorldQueries.getUSACitiesOrderedByDistrictAndPopulation();
			ResultSet rs = dbm.ExecuteResultSet(query);
			while(rs.next()) {
				
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String country = rs.getString("CountryCode");
				String district = rs.getString("District");
				int population = rs.getInt("Population");
				
				sb.append("<tr><td>" + id + "</td>"
						+ "<td>" + name + "</td>"
								+ "<td>" + country + "</td>"
										+ "<td>" + district + "</td>"
												+ "<td>" + population + "</td></tr>");
			}
			
			sb.append("</table>");
		}
		catch(Exception e) {
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
