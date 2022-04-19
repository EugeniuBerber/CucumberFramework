package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task {
	
	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	// jdbc:mysql://hostname:port/db name
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";

	//display the information from nationality table
	//but display ID as nationality ID name as nationality name
	
	//@Test
		public void aFewSecondsTask() throws SQLException {
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from ohrm_nationality");
			ResultSetMetaData rsMetaData = rs.getMetaData();
			List<Map<String, String>> listData = new ArrayList<>();
			Map<String, String> mapData;

			while (rs.next()) {
				mapData = new LinkedHashMap<>();
				for(int i=1; i<= rsMetaData.getColumnCount(); i++) {
					String colName = rsMetaData.getColumnName(i);
					if (colName.equals("id")){
						colName = "Nationality ID";
					}else if((colName.equals("name"))){
						colName = "Nationality Name";
					}
					String value = rs.getObject(i).toString();
					mapData.put(colName, value);				
				}
//				mapData.put("Nationality ID", rs.getObject("id").toString());
//				mapData.put("Nationality Name", rs.getObject("name").toString());				
				listData.add(mapData);
			}
			System.out.println(listData);

			rs.close();
			st.close();
			conn.close();

		}
}
