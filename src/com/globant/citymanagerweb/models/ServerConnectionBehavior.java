package com.globant.citymanagerweb.models;

import java.sql.Connection;

/**
 * 
 * ServerConnectionBehavior allows polymorphic database connections
 * 
 * @author marcos.irisarri
 *
 */
public interface ServerConnectionBehavior {

	Connection getConnection();
	String getConnectionUrl();
	String getConnectionDetails();
	String getTablesSchemaQuery();
}
