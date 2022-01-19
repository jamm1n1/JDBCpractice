package com.uni.member.model.dao;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uni.common.JDBCTemplate;
import com.uni.member.model.dto.Member;

/* 이전 프로젝트에서 Dao가 맡은 업무
1) JDBC드라이버 등록
2) DB 연결 Connection 객체생성
3) SQL 실행
4) 처리결과에 따른 트랜잭션처리(commit, rollback)
5) 자원 반환

이 때, 실제로 dao가 처리해야 업무는 SQL문을 DB로 전달하여 실행하고 반환값을 받아오는
3번의 역할만을 진행해야 함. 
 --> 3번 이외에 1,2,4,5 역할을 분리해야 함.
 
+ 1,2,4,5의 업무는 어디서든 공통적으로 이루어지는 공통 업무
 --> 한번에 묶어서 처리해주자
 --> 공통업무를 처리하기 위한
 JDBCTemplate 클래스 생성.
*/

public class MemberDAO {


		
		public ArrayList<Member> selectAll(Connection conn){
			
			ArrayList<Member> list =null;
			
			 //DB연결할 객체
			Statement stmt = null;// 실행할 쿼리
			ResultSet rset = null;// select한후 결과값 받아올 객체
			
			String sql = "SELECT * FROM MEMBER";// 자동으로 세미콜론을 붙여 실행되므로 붙히지 않는다
			
			//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록 
			
			try {
		        
		         conn = new JDBCTemplate().getConnection();
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
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		    	  close(rset);
				  close(stmt);
		    	  
		      }
			return list;
		}
		

		public Member selectOne(Connection conn,String memberId) {
			Member m = null;
			
		      
		      PreparedStatement pstmt = null; 
		      
		      ResultSet rset = null; 
		      
		      String sql = "SELECT * FROM MEMBER WHERE USERID = ?"; //이곳을 ?로
			
		  	try {
		       
		         conn = new JDBCTemplate().getConnection();
		         // 127.0.0.1 로컬호스트 기본주소값 / 포트  1521:xe "STUDENT", "STUDENT" 은 sql에 있음
		         System.out.println("conn = "+conn ); //성공하면 connection정보, 실패하면 null값
		         
		         //3. 쿼리문을 실행할 statment 객체생성
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, memberId);
		         //4. 쿼리문을 전송, 실행결과를 ResultSet으로받기
		         rset = pstmt.executeQuery();
		        
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
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		    	  close(rset);
				  close(pstmt);
				  
		      }
			return m;
		}
		public List<Member> selectName(Connection conn,String memberName) {
						
			List<Member> list =null;
			 
		      
			 PreparedStatement pstmt = null; 
		      
		      ResultSet rset = null; 
		      
		      String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
			
		  	try {
		         
		         conn =  new JDBCTemplate().getConnection();
		         // 127.0.0.1 로컬호스트 기본주소값 / 포트  1521:xe "STUDENT", "STUDENT" 은 sql에 있음
		         System.out.println("conn = "+conn ); //성공하면 connection정보, 실패하면 null값
		         
		         //3. 쿼리문을 실행할 statment 객체생성
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, "%"+memberName+"%");
		         //4. 쿼리문을 전송, 실행결과를 ResultSet으로받기
		         rset = pstmt.executeQuery();
		        
		         
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
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		    	  close(rset);
				  close(pstmt);
				 
		      }
			return list;
		}


		public int insertMember(Connection conn,Member m) {
			int result = 0;
			
		   
			PreparedStatement pstmt = null; 
			
			String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";			 
					/* +"'"+m.getUserId() +"',"			
					 +"'"+m.getPassword()+"',"
					 +"'"+m.getUserName()+"',"
					 +"'"+m.getGender()+"',"
					 +m.getAge()+","
					 +"'"+m.getEmail()+"',"  
					 +"'"+m.getPhone()+"'," 
					 +"'"+m.getAddress()+"',"
					 +"'"+m.getHobby()+"',"
					 +"sysdate)";*/
			
			   // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	        try {
			
	        pstmt = conn.prepareStatement(sql);
	                
	        pstmt.setString(1, m.getUserId());
	        pstmt.setString(2, m.getPassword());
	        pstmt.setString(3, m.getUserName());
	        pstmt.setString(4, m.getGender());
	        pstmt.setInt(5, m.getAge());
	        pstmt.setString(6, m.getEmail());
	        pstmt.setString(7, m.getPhone());
	        pstmt.setString(8, m.getAddress());
	        pstmt.setString(9, m.getHobby());
	      
	        
	       
	        
	        result = pstmt.executeUpdate();//처리한 행의 갯수 리턴
	        
	          
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
					close(pstmt);
							
			}
			return result;
			
		
}


		public int updateMember(Connection conn,Member m) {
			int result = 0;
			
				     
			PreparedStatement pstmt = null; 
			
			String sql = "UPDATE MEMBER SET PASSWORD = ? , EMAIL = ? ,PHONE = ?,ADDRESS = ?  WHERE USERID = ?";
			
			   // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	        try {
				
	        
	        
	       
	        pstmt = conn.prepareStatement(sql);
	      
	        
	        pstmt.setString(1,m.getPassword() );
	        pstmt.setString(2,m.getEmail() );
	        pstmt.setString(3,m.getPhone() );
	        pstmt.setString(4,m.getAddress() );
	        pstmt.setString(5,m.getUserId() );
	        
	        result = pstmt.executeUpdate();//처리한 행의 갯수 리턴
	        
	        if(result > 0) conn.commit();
	        else conn.rollback();
	         
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
					close(pstmt);
			
				
			}
			return result;	
		}


		public int deleteMember(Connection conn,String memberId) {
			int result = 0;
			 
		      
		      PreparedStatement pstmt = null; 
		      		 			
		      String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		      System.out.println(sql);
			
			try {
		        
		         pstmt = conn.prepareStatement(sql);	               
		         
		         
		         pstmt.setString(1, memberId);
		         result = pstmt.executeUpdate();//처리한 행의 갯수 리턴
		         
		         if(result > 0) conn.commit();
		         else conn.rollback();
		         
		 		}catch (Exception e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}finally {
		 			
		 				close(pstmt);
		 			
		 			
		 		}
		 		return result;	
		 	}


		public ArrayList<Member> delselectAll(Connection conn) {
			ArrayList<Member> list =null;
			
			 
			Statement stmt = null;
			ResultSet rset = null;
			
			String sql = "SELECT * FROM MEMBER_DEL";
			
			
			
			try {
		        
		         conn = new JDBCTemplate().getConnection();
		         
		         System.out.println("conn = "+conn ); 
		         
		         
		         stmt = conn.createStatement();
		         
		         
		         rset = stmt.executeQuery(sql);
		         
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
		         
		      } catch (SQLException e) {
		         
		         e.printStackTrace();
		      }finally {
		    	  close(rset);
				  close(stmt);
		    	  
		      }
			return list;
		}
		}

