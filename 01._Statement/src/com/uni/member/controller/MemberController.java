package com.uni.member.controller;

import java.util.ArrayList;

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
	Member m = new MemberDao().selectName(memberName);
	
	if(m != null) {
		menu.displayMember(m);
	}else {
		menu.displayError(memberName + "에 해당하는 데이터가 없습니다.");
	}
}

}
