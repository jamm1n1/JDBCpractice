package com.uni.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
   
   private static Connection conn = null;
   
   //DB 연결을 위해 공용 커넥션 객체 반환해주는 메소드
   public static Connection getConnection() {
      
      // 연결 안 되어있으면 연결해주기
      if(conn == null) {
    	
         try {           
        	 
        	 Properties prop = new Properties();
        	 
        	
        	prop.load(new FileReader("resources/driver.properties"));
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
        	 //사용후에
            // conn이 널이거나 닫히지 않았으면 닫아주기
            conn.close();
         }
      } catch (Exception e) {
         
         e.printStackTrace();
      }
   }
   
   
   
   
   public static void close(Statement stmt) {
      
      try {
         if(stmt != null && !stmt.isClosed()) {
        	 //사용후
        	 // stmt이 널이거나 닫히지 않았으면 닫아주기
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
        	 // rest이 널이거나 닫히지 않았으면 닫아주기
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
        	 // conn이 널이거나 닫힌게아니라면 커밋해주기
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
        	// conn이 널이거나 닫힌게아니라면 롤백하기
            conn.rollback();
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
   
   
   
   
   
   
   
}