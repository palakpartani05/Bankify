package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//	public static void main(String[] args) throws SQLException {
//		getConnection();
//		System.out.println("Connection Successfull  ");
//	}
	public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Connection Successfull  ");
            
        } catch (Exception e) {
            e.printStackTrace();
//            return null;
        }
        return DriverManager.getConnection("jdbc:derby:C:\\Users\\tusha\\bms;create=true");
//        jdbc:derby:C:\Users\tusha\bms;create=true
    }
}
