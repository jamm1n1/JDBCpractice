package com.uni.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDao;
import com.uni.member.model.dto.Member;
import com.uni.member.view.MemberMenu;

public class MemberController {

   public void selectAll() {
      MemberMenu menu = new MemberMenu();
      ArrayList<Member> list = new MemberDao().selectAll();
      
      if(!list.isEmpty()) {
    	  menu.displayMemberList(list);
      }else {
    	  menu.displayError("해당하는 데이터가 없습니다.");
      }
   }

public void selectOne(String memberId) {
	 MemberMenu menu = new MemberMenu();
	Member m = new MemberDao().selectOne(memberId);
	
	if(m != null) {
		menu.displayMember(m);
	}else {
		menu.displayError(memberId + "에 해당하는 데이터가 없습니다.");
	}
}

public void selectName(String memberName) {
	MemberMenu menu = new MemberMenu();
	List<Member> list= new MemberDao().selectName(memberName);
	
	if(!list.isEmpty()) {
		menu.displayMemberList(list);
	}else {
		menu.displayError(memberName + "에 해당하는 데이터가 없습니다.");
	}
}

public void insertMember(Member m) {
	int result = new MemberDao().insertMember(m);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원가입성공.");
    }else {
    	new MemberMenu().displayError("회원가입실패.");
    }
}

public void updateMember(Member m) {

	int result = new MemberDao().updateMember(m);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원수정성공.");
    }else {
    	new MemberMenu().displayError("회원수정실패.");
    }
}

public void deleteMember(String userId) {
	
	int result = new MemberDao().deleteMember(userId);
	System.out.println(result);
    if(result>0) {
    	new MemberMenu().displaySuccess("회원삭제성공.");
    }else {
    	new MemberMenu().displayError("회원삭제실패.");
		    }
}
}






