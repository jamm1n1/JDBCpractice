package com.uni.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.uni.member.model.dto.Member;
/* 1.Connection 객체연결하기 
 * 2.Statement 객체 생성하기 
 * 3.ResultSet 객체 생성하기 
 * 4.Sql작성하기 
 * 5.ResultSet  결과담기 
 * 6.list 에 객체 하나씩 담기 
 * 7.close 하기 (자원반납 - 생성의 역순)
 */
public class MemberDao {

	
	public ArrayList<Member> selectAll(){
		
		ArrayList<Member> list =null;
		
		Connection conn = null; //DB연결할 객체
		Statement stmt = null;// 실행할 쿼리
		ResultSet rset = null;// select한후 결과값 받아올 객체
		
		String sql = "SELECT * FROM MEMBER";// 자동으로 세미콜론을 붙여 실행되므로 붙히지 않는다
		
		//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록 
		
		try {
	         // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("드라이버 등록 성공");
	         
	         
	         // 2.등록된 클래스를 이용해서 db연결
	         // 드라이버타입@ip주소:포트번호:db이름(SID)
	         // orcl:사용자정의설치 , thin : 자동으로 설치 //ip주소 -> localhost 로 변경해도됨
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","STUDENT", "STUDENT");
	         // 127.0.0.1 로컬호스트 기본주소값 / 포트  1521:xe "STUDENT", "STUDENT" 은 sql에 있음
	         System.out.println("conn = "+conn ); //성공하면 connection정보, 실패하면 null값
	         
	         //3. 쿼리문을 실행할 statment 객체생성
	         stmt = conn.createStatement();
	         
	         //4. 쿼리문을 전송, 실행결과를 ResultSet으로받기
	         rset = stmt.executeQuery(sql);
	         //5 받은 결과값을 객체에 옮겨서 저장하기
	         list = new ArrayList<Member>();
	         while(rset.next()) {
	        	 Member m = new Member();
	        //https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
	        	 m.setUserId(rset.getString("USERID"));
	        	 m.setPassword(rset.getString("PASSWORD"));
	        	 m.setUserName(rset.getString("USERNAME"));
	        	 m.setGender(rset.getString("GENDER"));
	        	 m.setAge(rset.getInt("AGE"));
	        	 m.setEmail(rset.getString("EMAIL"));
	        	 m.setPhone(rset.getString("PHONE"));
	        	 m.setAddress(rset.getString("ADDRESS"));
	        	 m.setHobby(rset.getString("HOBBY"));
	        	 m.setEnrollDate(rset.getDate("ENROLLDATE"));
	        	 list.add(m);
	         }
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  try {
			  rset.close();		
	    	  stmt.close();
	    	  conn.close();
	    	  } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	      }
	      }
		return list;
	}
	

	public Member selectOne(String memberId) {
		Member m = null;
		 Connection conn = null;
	      
	      Statement stmt = null; 
	      
	      ResultSet rset = null; 
	      
	      String sql = "SELECT * FROM MEMBER WHERE USERID = '" + memberId +"'";
		
	  	try {
	         // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("드라이버 등록 성공");
	         
	         
	         // 2.등록된 클래스를 이용해서 db연결
	         // 드라이버타입@ip주소:포트번호:db이름(SID)
	         // orcl:사용자정의설치 , thin : 자동으로 설치 //ip주소 -> localhost 로 변경해도됨
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","STUDENT", "STUDENT");
	         // 127.0.0.1 로컬호스트 기본주소값 / 포트  1521:xe "STUDENT", "STUDENT" 은 sql에 있음
	         System.out.println("conn = "+conn ); //성공하면 connection정보, 실패하면 null값
	         
	         //3. 쿼리문을 실행할 statment 객체생성
	         stmt = conn.createStatement();
	         
	         //4. 쿼리문을 전송, 실행결과를 ResultSet으로받기
	         rset = stmt.executeQuery(sql);
	        
	         while(rset.next()) {
	        	 m = new Member();
	        //https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
	        	 m.setUserId(rset.getString("USERID"));
	        	 m.setPassword(rset.getString("PASSWORD"));
	        	 m.setUserName(rset.getString("USERNAME"));
	        	 m.setGender(rset.getString("GENDER"));
	        	 m.setAge(rset.getInt("AGE"));
	        	 m.setEmail(rset.getString("EMAIL"));
	        	 m.setPhone(rset.getString("PHONE"));
	        	 m.setAddress(rset.getString("ADDRESS"));
	        	 m.setHobby(rset.getString("HOBBY"));
	        	 m.setEnrollDate(rset.getDate("ENROLLDATE"));
	        	 
	         }
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  try {
			  rset.close();		
	    	  stmt.close();
	    	  conn.close();
	    	  } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	      }
	      }
		return m;
	}
	public Member selectName(String memberName) {
					
		 Member m = null;
		 Connection conn = null;
	      
	      Statement stmt = null; 
	      
	      ResultSet rset = null; 
	      
	      String sql = "SELECT * FROM MEMBER WHERE USERNAME = '" + memberName +"'";
		
	  	try {
	         // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("드라이버 등록 성공");
	         
	         
	         // 2.등록된 클래스를 이용해서 db연결
	         // 드라이버타입@ip주소:포트번호:db이름(SID)
	         // orcl:사용자정의설치 , thin : 자동으로 설치 //ip주소 -> localhost 로 변경해도됨
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","STUDENT", "STUDENT");
	         // 127.0.0.1 로컬호스트 기본주소값 / 포트  1521:xe "STUDENT", "STUDENT" 은 sql에 있음
	         System.out.println("conn = "+conn ); //성공하면 connection정보, 실패하면 null값
	         
	         //3. 쿼리문을 실행할 statment 객체생성
	         stmt = conn.createStatement();
	         
	         //4. 쿼리문을 전송, 실행결과를 ResultSet으로받기
	         rset = stmt.executeQuery(sql);
	        
	         while(rset.next()) {
	        	 m = new Member();
	        //https://www.tutorialspoint.com/jdbc/jdbc-data-types.htm
	        	 m.setUserId(rset.getString("USERID"));
	        	 m.setPassword(rset.getString("PASSWORD"));
	        	 m.setUserName(rset.getString("USERNAME"));
	        	 m.setGender(rset.getString("GENDER"));
	        	 m.setAge(rset.getInt("AGE"));
	        	 m.setEmail(rset.getString("EMAIL"));
	        	 m.setPhone(rset.getString("PHONE"));
	        	 m.setAddress(rset.getString("ADDRESS"));
	        	 m.setHobby(rset.getString("HOBBY"));
	        	 m.setEnrollDate(rset.getDate("ENROLLDATE"));
	        	 
	         }
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  try {
			  rset.close();		
	    	  stmt.close();
	    	  conn.close();
	    	  } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	      }
	      }
		return m;
	}
	}
