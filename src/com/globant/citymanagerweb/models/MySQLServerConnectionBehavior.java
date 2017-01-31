package com.globant.citymanagerweb.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLServerConnectionBehavior extends DBUserInfo implements ServerConnectionBehavior {

	public MySQLServerConnectionBehavior() {
		super();
	}
	
	public MySQLServerConnectionBehavior(String uid, String pwd, String cat) {
		super(uid, pwd, cat);
	}
	
	@Override
	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(getConnectionUrl());
			return conn;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getConnectionUrl() {
		return String.format("jdbc:mysql://localhost/%s?user=%s&password=%s", getCatalog(), getUserId(), getPassword());
	}

	@Override
	public String getConnectionDetails() {
		return "MySQL Database Connection to " + getCatalog();
	}

	@Override
	public String getTablesSchemaQuery() {
		return "select table_name from information_schema.tables where table_schema = " + getCatalog();
	}

}
