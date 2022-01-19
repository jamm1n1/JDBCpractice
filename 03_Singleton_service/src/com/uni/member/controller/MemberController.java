package com.uni.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDAO;
import com.uni.member.model.dto.Member;
import com.uni.member.model.service.MemberService;
import com.uni.member.view.MemberMenu;

public class MemberController {

   public void selectAll() {
      MemberMenu menu = new MemberMenu();
      ArrayList<Member> list = new MemberService().selectAll();
      // 다중으로 받을거기떄문에 list사용 
      
      if(!list.isEmpty()) {
    	  menu.displayMemberList(list);
    	  //리스트가 비어있지 않으면 리스트를 메뉴에 전달
      }else {
    	  menu.displayError("해당하는 데이터가 없습니다.");
      }
   }

public void selectOne(String memberId) {
	 MemberMenu menu = new MemberMenu();
	Member m = new MemberService().selectOne(memberId);
	//단일행을 원하기 떄문에 리스트를 굳이 사용할이유는 없음 
	
	if(m != null) {
		menu.displayMember(m);
	}else {
		menu.displayError(memberId + "에 해당하는 데이터가 없습니다.");
	}
}

public void selectName(String memberName) {
	MemberMenu menu = new MemberMenu();
	List<Member> list= new MemberService().selectName(memberName);
	//이름이 중복되지 않도록하기
	
	if(!list.isEmpty()) {
		menu.displayMemberList(list);
		//메뉴로 돌려줌
	}else {
		menu.displayError(memberName + "에 해당하는 데이터가 없습니다.");
	}
}

public void insertMember(Member m) {
	int result = new MemberService().insertMember(m);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원가입성공.");
    }else {
    	new MemberMenu().displayError("회원가입실패.");
    }
}

public void updateMember(Member m) {

	int result = new  MemberService().updateMember(m);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원수정성공.");
    }else {
    	new MemberMenu().displayError("회원수정실패.");
    }
}

public void deleteMember(String userId) {
	
	int result = new MemberService().deleteMember(userId);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원삭제성공.");
    }else {
    	new MemberMenu().displayError("회원삭제실패.");
		    }
}

public void exitprogram() {
	new MemberService().exitProgram();
	
}

public void delselectAll() {
    MemberMenu menu = new MemberMenu();
    List<Member> list = new MemberService().delselectAll();
    
    if(!list.isEmpty()) {
  	  menu.displayMemberList(list);
    }else {
  	  menu.displayError("해당하는 데이터가 없습니다.");
    }
	
}
}