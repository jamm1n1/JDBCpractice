package com.uni.member.model.dao;

import static com.uni.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.uni.common.JDBCTemplate;
import com.uni.member.model.dto.Member;
import com.uni.member.model.exception.MemberException;

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

//기본생성자 작성전에 sql구문을 읽어올 properties 참조변수를 선언
private Properties prop = null;

//외부에서 , .properties 파일을 읽어와 prop 참조변수에 저장 
 public MemberDAO() {
	 
	 
		
	 try {
		 prop = new Properties();
		prop.load(new FileReader("resources/query.properties"));
		//파일리더를 사용해서 query.properties 여기서 로드해기
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 
 }

		public ArrayList<Member> selectAll(Connection conn) throws MemberException{
			
			ArrayList<Member> list =null;
			
			 //DB연결할 객체
			Statement stmt = null;// 실행할 쿼리
			ResultSet rset = null;// select한후 결과값 받아올 객체
			
			//String sql = "SELECT * FROM MEMBER";// 자동으로 세미콜론을 붙여 실행되므로 붙히지 않는다
			// 입력 값을 ? 로 바꿔 위치 홀더 만들어 줌
			String sql = prop.getProperty("selectAll");
			//sql을 실행하는데 파일에 적어둔 쿼리프로퍼티지를삽입함
			
			//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록 
			
			try {

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
		         
		      } catch (Exception e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		         throw new MemberException("selectAll에러 : " +e.getMessage());
		      }finally {
		    	  close(rset); // 템플릿에 close 메소드에 보내줘서 닫음 스태틱으로 임포트선언해서 앞에 안붙여줘도댐
				  close(stmt);
		    	  
		      }
			return list;
			// 디비에서 가져온걸 다시 서비스로 넘기자 
		}
		

		public Member selectOne(Connection conn,String memberId) throws MemberException {
			Member m = null;
			
		      
		      PreparedStatement pstmt = null; 
		      
		      ResultSet rset = null; 
		      
		      //String sql = "SELECT * FROM MEMBER WHERE USERID = ?"; //이곳을 ?로
		      String sql = prop.getProperty("selectOne");
			
		  	try {
		       		         
		         
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
		         throw new MemberException("selectOne에러 : " +e.getMessage());
		      }finally {
		    	  close(rset); // 사용다햇고 템플릿에 정의했으니 이런식으로쓰기  스태틱으로 임포트선언해서 앞에 안붙여줘도댐
				  close(pstmt);
				  
		      }
			return m;
			//리턴해서 다시 서비스로 
		}
		public List<Member> selectName(Connection conn,String memberName) throws MemberException {
						
			List<Member> list =null;
			 
		      
			 PreparedStatement pstmt = null; 
		      
		      ResultSet rset = null; 
		      
		     // String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		      String sql = prop.getProperty("selectName");
			
		  	try {
		         
		      
		         
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
		         throw new MemberException("selectName에러 : " +e.getMessage());
		      }finally {
		    	  close(rset);
				  close(pstmt);
				 
		      }
			return list;
			//리턴해서 다시 서비스로 
		}


		public int insertMember(Connection conn,Member m) throws MemberException {
			int result = 0;
			
		   
			PreparedStatement pstmt = null; 
			
			//String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";	
			String sql = prop.getProperty("insertProduct");
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
				 throw new MemberException("insertMember에러 : " +e.getMessage());
			}finally {
				
					close(pstmt);
							//리절트값을 담지않았으니까 안쓴다 
			}
			return result;
			//리턴해서 다시 서비스로 
		
}


		public int updateMember(Connection conn,Member m) throws MemberException {
			int result = 0;
			
				     
			PreparedStatement pstmt = null; 
			
			//String sql = "UPDATE MEMBER SET PASSWORD = ? , EMAIL = ? ,PHONE = ?,ADDRESS = ?  WHERE USERID = ?";
			String sql = prop.getProperty("updateMember");
			// 파일에 저장해놓은걸 프로퍼티러가져와서 쓰기 
			   // 1. JDBC DRIVER 등록처리: 해당 DATABASE 벤더 사가 제공하는 클래스 동록
	        try {
				
	        
	        //result set이 필요없다 실행결과를 반환받을 것이 아니기 떄문에 
	       
	        pstmt = conn.prepareStatement(sql);
	      
	        
	        pstmt.setString(1,m.getPassword() ); //업데이트 할 것들
	        pstmt.setString(2,m.getEmail() );
	        pstmt.setString(3,m.getPhone() );
	        pstmt.setString(4,m.getAddress() );
	        pstmt.setString(5,m.getUserId() );
	        
	        result = pstmt.executeUpdate();//처리한 행의 갯수 리턴
	        //위에서 sql 로 받아서  sql이 필요없음
	        
	       
	         
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new MemberException("updateName에러 : " +e.getMessage());
			}finally {
				
					close(pstmt);  // 다썻으니까 닫고 스태틱으로 임포트선언해서 앞에 안붙여줘도댐
			
				
			}
			return result;	
			// 서비스로 보내주기
		}


		public int deleteMember(Connection conn,String memberId) throws MemberException {
			int result = 0;
			 
		      
		      PreparedStatement pstmt = null; 
		      		 			
		      //String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		      String sql = prop.getProperty("deleteMember");
		      // 파일에 저장해놓은걸 프로퍼티러가져와서 쓰기 
		      System.out.println(sql);
			
			try {
		        
		         pstmt = conn.prepareStatement(sql);	               
		         
		         
		         pstmt.setString(1, memberId);
		         result = pstmt.executeUpdate();//처리한 행의 갯수 리턴
		         		      
		         
		 		}catch (Exception e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 			throw new MemberException("selectMember에러 : " +e.getMessage());
		 		}finally {
		 			
		 				close(pstmt);
		 			
		 			
		 		}
		 		return result;	
		 		//값을 서비스로 
		 	}


		public ArrayList<Member> delselectAll(Connection conn) throws MemberException {
			ArrayList<Member> list =null;
			
			 
			Statement stmt = null;
			ResultSet rset = null;
			// 삭제된 인원을 받아야하니까 리절셋쓰고
			//String sql = "SELECT * FROM MEMBER_DEL";
			String sql = prop.getProperty("delselectAll");
			
			
			try {
		        	        		         		       
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
		         throw new MemberException("delselectAll에러 : " +e.getMessage());
		         
		      }finally {
		    	  close(rset);
				  close(stmt);
		    	  
		      }
			return list;
			// 서비스로 다시반환
		}
		}

