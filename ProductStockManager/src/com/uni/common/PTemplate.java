package com.uni.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class PTemplate {

	 private static Connection conn = null;
	   
	
	   public static Connection getConnection() {
	      
	     
	      if(conn == null) {
	    	  Properties prop = new Properties();
	         try {
	        	prop.load(new FileReader("resourse/driver.properties"));
	            Class.forName(prop.getProperty("driver"));
	            conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
	            
	            conn.setAutoCommit(false);
	            
	         } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	         
	      }
	      
	      return conn;
	   }
	   
	   
	   
	   
	   public static void close(Connection conn) {
	      
	      try {
	         if(conn != null && !conn.isClosed()) {
	            
	            conn.close();
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   public static void close(Statement stmt) {
	      
	      try {
	         if(stmt != null && !stmt.isClosed()) {
	            
	            stmt.close();
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   public static void close(ResultSet rest) {
	      
	      try {
	         if(rest != null && !rest.isClosed()) {
	            
	            rest.close();
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   public static void commit(Connection conn) {
	      
	      try {
	         if(conn != null && !conn.isClosed()) {
	            
	            conn.commit();
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	   }
	   
	   
	   
	   
	   public static void rollback(Connection conn) {
	      
	      try {
	         if(conn != null && !conn.isClosed()) {
	            
	            conn.rollback();
	         }
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	   }
	   
	   
}
