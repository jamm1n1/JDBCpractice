package com.uni.member.model.service;

import static com.uni.common.JDBCTemplate.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.uni.member.model.dao.MemberDAO;
import com.uni.member.model.dto.Member;
import com.uni.member.model.exception.MemberException;

/* Service 클래스에서 메소드 작성 방법
 * 1) Controller로 부터 인자를 전달받음
 * 2) Connection 객체 생성
 * 3) Dao 객체 생성
 * 4) Dao로 생성한 Connection 객체와 인자를 전달
 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */
public class MemberService {
   
	public ArrayList<Member> selectAll() throws MemberException {
		Connection conn = getConnection();		
		//커넥션 객체생성
		//커넥션 객체랑 컨트롤러에서 받은거를 다오로 넘김 
		ArrayList<Member> list = new MemberDAO().selectAll(conn);
		// 다오에서 커넥션을 정리한게아니라서 커넥션을 같이보내주는거같다.
		//다중행을 받을거라서 리스트사용함
		//다오 객체를 생성
		//다오한테 결과받고 서비스로 넘겨
		return list;
	
	}

	public Member selectOne(String memberId) throws MemberException {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn,memberId);
		// 컨트롤러에서받은거 다오로 전달
		// 다오에서 커넥션을 정리한게아니라서 커넥션 같이보내주는거같다.
		// 멤버아이디로 조회할거라서 아이디 넘겨주고 
		//하나의 값만 받을거라서 리스트사용안함
		return m;
		//컨트롤러로 
	}

	public List<Member> selectName(String memberName) throws MemberException {
		Connection conn = getConnection();
		List<Member> list = new MemberDAO().selectName(conn,memberName);
		// 이름은 중복일 수 있으니까 리스트로 받고 다오로 넘겨주기 
		return list;
	 //결과값 받아서 넘기기//컨트롤러로 
	}

	public int insertMember(Member m) throws MemberException {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn,m);
		// 컨트롤러에서받은거 다오로 전달
		//dml이니까 트랙잭션하기
				if(result >0) commit(conn);
				
				else rollback(conn);
		
				//DML 제대로 실행되면 커밋. 안 되면 롤백		
		return result;	
//컨트롤러 주기
	}

	public int updateMember(Member m) throws MemberException {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn,m);
		// 컨트롤러에서받은거 다오로 전달
		//다오의 업데이트멤버에 전달 
		//결과값을 받고 
		if(result >0) commit(conn);
		else rollback(conn);
		return result;
		//결과값을 컨트롤러에 
	}

	public int deleteMember(String userId) throws MemberException {
		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn,userId);
		// 컨트롤러에서받은거 다오로 전달
		// dml 이니가 트랜잭션처리
		if(result >0) commit(conn);
		else rollback(conn);
		return result;
	}

	public void exitProgram() {
		close(getConnection());
		// 커넥션을 사용종료와 함께닫음
		
	}

	public List<Member> delselectAll() throws MemberException {
	Connection conn = getConnection();		
		
		ArrayList<Member> list = new MemberDAO().delselectAll(conn);
		// 컨트롤러에서받은거 다오로 전달
		return list;
		//삭제한 인원들 가져와야하니까 리스트로 받고 
		//다오로 넘겨서 결과값 받아서 컨트롤러 주기 
	}

	
	

}

