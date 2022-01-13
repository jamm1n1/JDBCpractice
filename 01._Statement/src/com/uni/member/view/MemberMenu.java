package com.uni.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.uni.member.controller.MemberController;
import com.uni.member.model.dto.Member;

public class MemberMenu {

	private static Scanner sc = new Scanner(System.in);
	private MemberController mController = new MemberController();
	
	public void mainMenu() {
		
		int choice;
		
		do {
			System.out.println("\n**********회원관리프로그램************");
			System.out.println("1.회원 전체조회"); //SELECT
			System.out.println("2.회원 아이디 조회");//SELECT
			System.out.println("3.회원 이름 조회");//SELECT
			System.out.println("4.회원 가입");//INSERT
			System.out.println("5.회원정보 변경");//UPDATE
			System.out.println("6.회원 탈퇴");//DELETE
			System.out.println("9.프로그램 종료");
			System.out.println("번호선택 : ");
			
			choice = sc.nextInt();
			
			switch(choice) {
			case 1 :
				mController.selectAll();
				break;
			case 2 :
				mController.selectOne(inputMemberId());
				break;
			case 3 :
				mController.selectName(inputMemberName());
				break;
			case 4 :
				
				break;
			case 5 :
				
				break;
			case 6 :
				
				break;
			case 9 :
				System.out.println("정말로 끝내시겠습니까?(y/n)");
				if('y'==sc.next().toLowerCase().charAt(0)) {
					return;
				}
				
				
			default :
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}while(true);
		
		
	}
   private String inputMemberName() {
	   System.out.println("이름입력 : ");
		return sc.next();
	}
private String inputMemberId() {
		
	   System.out.println("아이디입력 : ");
		return sc.next();
	}
// 리턴된 리스트 출력용 메소드
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 전체 회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t비밀번호\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("----------------------------------------------------------");
		
		for(Member m : list) {
			System.out.println(m);
		}
		
	}
	//실패에 대한 에러메세지 출력용 메소드 
	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패 : "+ message);
		
	}
	public void displayMember(Member m) {
		System.out.println("\n 조회된 회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("----------------------------------------------------------");
		System.out.println(m);
	}
}
