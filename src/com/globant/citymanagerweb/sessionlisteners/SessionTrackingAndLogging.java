package com.globant.citymanagerweb.sessionlisteners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class SessionTrackingAndLogging
 *
 */
@WebListener
public class SessionTrackingAndLogging implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionTrackingAndLogging() {
        //instantiate log
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent hsbe)  { 
    	
    	//attribute added to session
    	System.out.printf("Attribute added to session: %s\t%s\n", hsbe.getName(), hsbe.getValue());
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent hsbe)  { 

    	//attribute removed from session
    	System.out.printf("Attribute removed from session: %s\t%s\n", hsbe.getName(), hsbe.getValue());
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent hsbe)  { 

    	//attribute updated in session
    	System.out.printf("Attribute updated in session: %s\t%s\n", hsbe.getName(), hsbe.getValue());
    }
	
}
