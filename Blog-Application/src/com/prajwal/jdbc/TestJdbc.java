package com.prajwal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcurl="jdbc:mysql://localhost:3306/blog_application?useSSL=false";
;
		String user="root";
		String pass="";
		try {
			System.out.println("Connecting to database:"+jdbcurl);
			Connection myConn=DriverManager.getConnection(jdbcurl,user,pass);
			System.out.println("connection establish");
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}