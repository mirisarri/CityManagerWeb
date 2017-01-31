package com.globant.citymanagerweb.helpers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.globant.citymanagerweb.models.DBManager;
import com.globant.citymanagerweb.models.MySQLServerConnectionBehavior;
import com.globant.citymanagerweb.models.ServerConnectionBehavior;

public class DBManagerSetup implements ServletContextListener {

	private DBManager dbm = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		//clean up the connection when the context is destroyed
		if(dbm != null && dbm.isConnected()) {
			dbm.closeConnection(false);
		}
		dbm = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		//access the servlet context from the event argument (renamed sce)
		//get db conn info from context init params
		ServletContext sc = sce.getServletContext();
		String uid = sc.getInitParameter("dbuserid");
		String pwd = sc.getInitParameter("dbuserpwd");
		String cat = sc.getInitParameter("dbinitcat");
		
		//set the scb for mySQL
		ServerConnectionBehavior scb = new MySQLServerConnectionBehavior(uid, pwd, cat);
		
		//create the manager
		dbm = new DBManager(scb);
		
		//put the manager into the servlet context attributes for global use in the application
		sc.setAttribute("WorldDBManager", dbm);
		
		System.out.println("WorldDBManager created and added to context");
	}
}
