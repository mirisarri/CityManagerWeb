package com.globant.citymanagerweb.sessionlisteners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ActiveUserTracking
 *
 */
@WebListener
public class ActiveUserTracking implements HttpSessionListener {

	private static int activeUsers;
    /**
     * Default constructor. 
     */
    public ActiveUserTracking() {
        
    }

    public static int getActiveUsersCount() {
    	return activeUsers;
    }
    
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent hse)  { 
    	System.out.println("A new session was created");
    	activeUsers++;
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("An existing session was destroyed");
    	activeUsers--;
    }
}
