package com.uni.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDAO;
import com.uni.member.model.dto.Member;
import com.uni.member.model.exception.MemberException;
import com.uni.member.model.service.MemberService;
import com.uni.member.view.MemberMenu;

public class MemberController {

   public void selectAll() {
      MemberMenu menu = new MemberMenu();
      ArrayList<Member> list;
	try {
		list = new MemberService().selectAll();
	  
      if(!list.isEmpty()) {
    	  
    	  menu.displayMemberList(list);
      }else {
    	  menu.displayError("해당하는 데이터가 없습니다.");
      }
      } catch (MemberException e) {
        menu.displayError("회원조회 실패 관리자에게 문의하세요");
  		System.out.println(e.getMessage());
   }
   }
public void selectOne(String memberId) {
	 MemberMenu menu = new MemberMenu();
	Member m;
	try {
		m = new MemberService().selectOne(memberId);
	
	
	//하나만 받을거다 리스트사요안함 
	//가공처리해서 서비스로 
	if(m != null) {
		//m이 널이아니면 이거반환
		//아니면 밑에거
		menu.displayMember(m);
	}else {
		menu.displayError(memberId + "에 해당하는 데이터가 없습니다.");
	}
	 } catch (MemberException e) {
	        menu.displayError("회원조회 실패 관리자에게 문의하세요");
	  		System.out.println(e.getMessage());
	 }
}

public void selectName(String memberName) {
	MemberMenu menu = new MemberMenu();
	List<Member> list;
	try {
		list = new MemberService().selectName(memberName);
	
	// 이름은 중복이 있을수있으니까 리스트로 받고 
	// 
	if(!list.isEmpty()) {
		//비어있으면 데이터가없는거니까 엘스로 반환
		menu.displayMemberList(list);
	}else {
		menu.displayError(memberName + "에 해당하는 데이터가 없습니다.");
	}
	} catch (MemberException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void insertMember(Member m) {
	int result;
	try {
		result = new MemberService().insertMember(m);
	
		System.out.println(result);
	//다오에서 반환된 리절트값을 가져와서 비교 
    if(result>0) {
    	new MemberMenu().displaySuccess("회원가입성공.");
    }
    	
        }catch (MemberException e) {
    	new MemberMenu().displayError("회원가입실패.");
        }	
}


public void updateMember(Member m) {

	int result;
	try {
		result = new  MemberService().updateMember(m);
	System.out.println(result);
    if(result>0) {
    	//다오에서 수정에성공했으면 리절트를 올려주기때문에 성공하면 0보다크다 
    	//실패하면 리절트가 안올라가서 0
    	new MemberMenu().displaySuccess("회원수정성공.");
    }	} catch (MemberException e) {
    	new MemberMenu().displayError("회원수정실패.");
    }
}

public void deleteMember(String userId) {
	
	int result;
	try {
		result = new MemberService().deleteMember(userId);
	System.out.println(result);
    if(result>0) {
    	//여기도 마찬가지다 성공하면 올라가고 아니면 0이다
    	new MemberMenu().displaySuccess("회원삭제성공.");
    }
    } catch (MemberException e) {
		
    	new MemberMenu().displayError("회원삭제실패.");
    }	    
}

public void exitprogram() {
	new MemberService().exitProgram();
	//가공해서 서비스로 넘겨준다 
	
}

public void delselectAll() {
    MemberMenu menu = new MemberMenu();
    List<Member> list;
	try {
		list = new MemberService().delselectAll();
    
    if(!list.isEmpty()) {
    	//리스트가 안비어있으면 리스트가 있는거니까 반환하고 아니먄 else반환
  	  menu.displayMemberList(list);
    }else {
    	menu.displayNoData();
    }
    } catch (MemberException e) {
  	  menu.displayError("탈퇴회원조회실패.관리자한테 문의");
    }
	
}
}