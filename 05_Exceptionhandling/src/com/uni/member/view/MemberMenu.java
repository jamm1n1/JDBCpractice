package com.uni.member.view;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	import com.uni.member.controller.MemberController;
	import com.uni.member.model.dto.Member;

	public class MemberMenu {
//와리가리하는법 뷰-> 컨트롤러 -> 서비스 -> 다오 -> 디비 -> 다오 -> 서비스 -> 컨트롤러 -> 뷰
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
				System.out.println("7.탈퇴 회원 조회");
				System.out.println("9.프로그램 종료");
				System.out.println("번호선택 : ");
				
				choice = sc.nextInt();
				
				switch(choice) {
				case 1 :
					mController.selectAll();
					break;
				case 2 :
					mController.selectOne(inputMemberId());
			
					// 컨트롤러에서 가져오기 아이디 가져오기 그래서 출력하기
					
					break;
				case 3 :
					mController.selectName(inputMemberName());
					// 컨트롤러 일시키기
					break;
				case 4 :
					mController.insertMember(inputMember());
					// 컨트롤러에서 실행할수있게 
					break;
				case 5 :
					mController.updateMember(updateMember());
					// 컨트롤러에서 가져오기
					break;
				case 6 :
					mController.deleteMember(inputMemberId());
					// 컨트롤러에서 가져오기
					break;
				case 7 :
					mController.delselectAll();
				/*	@실습문제
					탈퇴회원을 별도의 테이블 MEMBER_DEL로 관리해보자.
					MEMBER_DEL은 MEMBER테이블의 모든 컬럼과 DEL_DATE(탈퇴일)컬럼을 추가로 가지고 있다.
					회원이 탈퇴하면, 자동으로 탈퇴테이블에 추가되도록 트리거를 생성하고,
					탈퇴회원조회를 메뉴에 추가하도록 하자.*/
				case 9 :
					System.out.println("정말로 끝내시겠습니까?(y/n)");
					if('y'==sc.next().toLowerCase().charAt(0)) {
						// 맞는지아닌지 
						mController.exitprogram();
						return;
					}
					
					
				default :
					System.out.println("번호를 잘못 입력하셨습니다.");
				}
			}while(true);
			
			
		}
	   private Member updateMember() {
		   Member m = new Member();
		   
		   m.setUserId(inputMemberId());
		   System.out.println("암호 : ");
		   m.setPassword(sc.next());
		   
		   System.out.println("이메일 : ");
		   m.setEmail(sc.next());
		   
		   System.out.println("전화번호 : ");
		   m.setPhone(sc.next());
		   sc.nextLine();
		   System.out.println("주소 : ");
		   m.setAddress(sc.nextLine());
		   return m;
		}
	private Member inputMember() {
		   Member m = new Member();
		   System.out.println("새로운 회원정보를 입력하세요 >>");
		   System.out.println("아이디 : ");
		   m.setUserId(sc.next());
		   System.out.println("암호 : ");
		   m.setPassword(sc.next());
		   System.out.println("이름 : ");
		   m.setUserName(sc.next());
		   System.out.println("나이 : ");
		   m.setAge(sc.nextInt());
		   sc.nextLine();
		   System.out.println("성별(M/F) : ");
		   m.setGender(sc.next().toUpperCase());
		   System.out.println("이메일 : ");
		   m.setEmail(sc.next());
		   System.out.println("전화번호(-뺴고) : ");
		   m.setPhone(sc.next());
		   System.out.println("주소 : ");
		   m.setAddress(sc.next());
		   System.out.println("취미(,로 공백없이) : ");
		   m.setHobby(sc.next());
		   
		   return m;
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
		public void displayMemberList(List<Member> list) {
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
		//아이디로 조회된멤버 회원 한명에 대한 정보를 출력하느 메소드
		public void displayMember(Member m) {		
			System.out.println("\n 조회된 회원정보는 다음과 같습니다.");
			System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
			System.out.println("----------------------------------------------------------");
			System.out.println(m);
		}
		//성공메세지출력
		public void displaySuccess(String message) {
			System.out.println("서비스 요청 결과 : "+message);
			
		}
	public void displayNoData() {
		System.out.println("조회된 데이터가 없습니다.");
	}
}
