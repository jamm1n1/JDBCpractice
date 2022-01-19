package com.uni.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.uni.member.controller.Controller;
import com.uni.member.dto.Product;
import com.uni.member.dto.ProductIO;





public class ProductMenu {

	
	private static Scanner sc = new Scanner(System.in);
	private Controller c = new Controller();
	
	public void mainMenu() {
		
		int choice;
		
		
		while(true) {
			System.out.println("\n**********회원관리프로그램************");
			System.out.println("1.전체조회"); //SELECT
			System.out.println("2.상품 추가");//insert
			System.out.println("3.상품 수정");//update
			System.out.println("4.상품 삭제");//delect
			System.out.println("5.상품 검색");//select
			System.out.println("6.상품 입출고메뉴");
			System.out.println("번호선택 : ");
			
			choice = sc.nextInt();
			
			switch(choice) {
			case 1 :
				c.selectAll();
				break;			
			case 2 :
				c.insertproduct(inputproduct());
				break;
			
			case 3 :
				c.updateproduct(updateproductName());
				break;
			
			case 4 :
				c.delectproduct(inputProductname());
				break;
			
			case 5 :
				c.productcheck(inputProductname());
				break;
			case 6 :
				 subMenu();
			default :
				System.out.println("번호를 잘못 입력하셨습니다.");
			
			}
		
		}
	}
		
			public void subMenu() {
				int choice2;
			
			while(true) {
				System.out.println("\n**********회원관리프로그램************");
				System.out.println("1.전체입출고내역확인"); //SELECT
				System.out.println("2.입고 내역만 조회");//SELECT
				System.out.println("3.출고 내역만 조회");//SELECT
				System.out.println("4.상품 입고"); 
				System.out.println("5.상품 출고");
				System.out.println("9.메인메뉴로 돌아가기");
				System.out.println("번호선택 : ");
				
				choice2 = sc.nextInt();
				
				switch(choice2) {
				case 1 :
					c.subselectAll();
					break;
				case 2 :
					c.selectenter();
					break;
				case 3 :
					c.selectrelese();
					break;
				case 4 :
					c.insertproducte(enterproduct());
					break;
				case 5 :
					c.releseproducte(releseproduct());
					break;
				case 9 :
					System.out.println("정말로 끝내시겠습니까?(y/n)");
					if('y'==sc.next().toLowerCase().charAt(0)) {
						
						return;
					}
					
				
				}
		}
			
			}
		
	
			

			private ProductIO releseproduct() {
				ProductIO p = new  ProductIO();
				
				   System.out.println("출고할 상품 입력 >>");			
				
				   System.out.println("상품 아이디: ");
				   p.setProductId(sc.next());	
				   System.out.println("출고할 수량: ");
				   p.setAmount(sc.nextInt());
				   
				return p;
			}

			private ProductIO enterproduct() {
				ProductIO p = new  ProductIO();
				   System.out.println("입고할 상품 입력 >>");			
				
				   System.out.println("상품 아이디: ");
				   p.setProductId(sc.next());				   			  
				   System.out.println("입고할 수량: ");
				   p.setAmount(sc.nextInt());
				  				   
				return p;
			}

			private String inputProductname() {
				  System.out.println("상품명 입력: ");
					return sc.next();
	}


			private Product updateproductName() {
				Product m = new  Product();
				   m.setpName(inputProductname());
						   
				   System.out.println("가격 : ");
				   m.setPrice(sc.nextInt());
				   sc.nextLine();
				   
				   System.out.println("수량 : ");
				   m.setStock(sc.nextInt());				  
				   
				   return m;
	}
			public void displayError(String message) {
				System.out.println("서비스 요청 처리 실패 : "+ message);
				
			}
					
			public void displayProductList1(ArrayList<Product> list) {
				System.out.println("\n 조회된 전체 회원정보는 다음과 같습니다.");
				System.out.println("\n상품아이디\t상품명\t가격\t시리즈\t수량");
				System.out.println("----------------------------------------------------------");
				
				for(Product m : list) {
					System.out.println(m);
				
			}
			}
			public void displaySuccess(String message) {
				System.out.println("서비스 요청 결과 : "+message);
				
			}
			private Product inputproduct() {
				 Product m = new  Product();
				   System.out.println("새로운 상품 입력 >>");
				   System.out.println("상품 아이디 : ");
				   m.setProductId(sc.next());
				   System.out.println("상풍명 : ");
				   m.setpName(sc.next());
				   System.out.println("가격 : ");
				   sc.nextLine();
				   m.setPrice(sc.nextInt());
				   System.out.println("시리즈 : ");
				   m.setDescription(sc.next());
				   System.out.println("수량 : ");
				   m.setStock(sc.nextInt());
				return m;
				   
}

			
				
			

			public void displayProduct(Product m) {
				System.out.println("\n 조회된 전체 회원정보는 다음과 같습니다.");
				System.out.println("\n상품아이디\t상품명\t가격\t시리즈\t수량");
				System.out.println("----------------------------------------------------------");
				System.out.println(m);
				
			}

			public void displayProductList2(ArrayList<ProductIO> list) {
				System.out.println("\n 조회된 전체 회원정보는 다음과 같습니다.");
				System.out.println("\n입출고번호\t상품ID\t상품명\t입출고일\t입출고수량\t상품명");
				System.out.println("----------------------------------------------------------");
				
				for(ProductIO m : list) {
					System.out.println(m);
				}
			}
}