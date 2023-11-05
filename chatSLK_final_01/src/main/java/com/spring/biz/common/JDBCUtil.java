//package com.spring.biz.common;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class JDBCUtil {
//	private static final String driverName="oracle.jdbc.driver.OracleDriver";
//	private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String userName="lee";
//	private static final String password="1234";
//
//	public static Connection connect() {
//		Connection conn=null;
//		
//		try {
//			Class.forName(driverName);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			conn=DriverManager.getConnection(url, userName, password);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return conn;
//	}
//
//	public static void disconnect(PreparedStatement pstmt,Connection conn) {
//		try {
//			pstmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	public static void disconnect(ResultSet rs,PreparedStatement pstmt,Connection conn) {
//		try {
//			rs.close();
//			pstmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
//
//
