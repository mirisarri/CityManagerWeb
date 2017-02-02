package com.globant.citymanagerweb.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private ServerConnectionBehavior scb = null;
	private static final int ERROR_CODE = -1;
	
	public DBManager() {
		
	}
	
	public DBManager(ServerConnectionBehavior connBehavior) {
		scb = connBehavior;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public String getConnectionURL() {
		return scb.getConnectionUrl();
	}
	
	public String getTablesSchemaQuery() {
		return scb.getTablesSchemaQuery();
	}
	
	public void setConnectionBehavior(ServerConnectionBehavior connBehavior) {
		
		if(connBehavior == null) {
			throw new IllegalArgumentException("Please use a valid connection behaviour");
		}
		
		scb = connBehavior;
	}
	
	public boolean openConnection() {
		try {
			
			if(scb == null) {
				throw new IllegalArgumentException("Define a connection behaviour");
			}
			if(conn != null) {
				closeConnection(false);
			}
			
			conn = scb.getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return isConnected();
	}
	
	public boolean closeConnection(boolean keepAlive) {
		
		try {
			
			if(conn != null) {
				if(!conn.isClosed()) {
					conn.close();
				}
			}
			
			if(!keepAlive) {
				conn = null;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean isConnected() {
		return conn != null;
	}
	
	public boolean executeNonQuery(String query) {
		
		try {
			Statement st = conn.createStatement();
			int res = st.executeUpdate(query);
			if(res == ERROR_CODE) {
				//Log it
				return false;
			}
			st.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public ResultSet ExecuteResultSet(String query) throws SQLException {
		
		PreparedStatement st = conn.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		return rs;
	}
}
