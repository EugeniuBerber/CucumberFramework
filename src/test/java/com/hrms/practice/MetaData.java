package com.hrms.practice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MetaData {

	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	// jdbc:mysql://hostname:port/db name
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";

	// @Test
	public void dbMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		DatabaseMetaData dbMetaData = conn.getMetaData();
		String driverName = dbMetaData.getDriverName();
		String version = dbMetaData.getDatabaseProductVersion();

		System.out.println(driverName);
		System.out.println(version);
		conn.close();
	}

	// @Test
	public void resultSetMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from hs_hr_employees where employee_id = 8392");
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		System.out.println(columnCount);
		String firstColName = rsMetaData.getColumnName(1);
		System.out.println(firstColName);

		for (int i = 1; i <= columnCount; i++) {
			String colName = rsMetaData.getColumnName(i);
			System.out.println(colName);
		}
		rs.close();
		st.close();
		conn.close();
	}

	// HW get the resultset metadata store in it in arraylist,
	// retrieve from arraylist and print in console
	// also get column type name and get table names

	@Test
	public void resultSetMetaDataArrayList() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from hs_hr_emp_dependents");
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		String tableName = rsMetaData.getTableName(1);
		System.out.println("Table name:");
		System.out.println(tableName);

		List<String> colNames = new ArrayList<>();
		List<String> colTypeNames = new ArrayList<>();
		int columnCount = rsMetaData.getColumnCount();

		for (int i = 1; i <= columnCount; i++) {
			String colName = rsMetaData.getColumnName(i);
			String colType = rsMetaData.getColumnTypeName(i);
			colNames.add(colName);
			colTypeNames.add(colType);
		}
		System.out.println("---------------");
		System.out.println("Column names:");
		for (String name : colNames) {
			System.out.println(name);
		}

		System.out.println("---------------");
		System.out.println("Column type names:");
		for (String name : colTypeNames) {
			System.out.println(name);
		}

		rs.close();
		st.close();
		conn.close();

	}

}
