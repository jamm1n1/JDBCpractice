package com.uni.member.controller;

import java.util.ArrayList;

import com.uni.member.dto.Product;
import com.uni.member.dto.ProductIO;
import com.uni.member.service.ProductService;

import com.uni.view.ProductMenu;

public class Controller {

	public void selectAll() {
		ProductMenu m = new ProductMenu();
		ArrayList<Product> list = new ProductService().selectAll();

		if (!list.isEmpty()) {
			m.displayProductList1(list);

		} else {
			m.displayError("해당하는 데이터가 없습니다.");
		}
	}

	public void insertproduct(Product p) {

		int result = new ProductService().insertproduct(p);
		System.out.println(result);
		if (result > 0) {
			new ProductMenu().displaySuccess("제품등록성공.");
		} else {
			new ProductMenu().displayError("제품등록실패.");
		}

	}

	public void updateproduct(Product p) {
		int result;

		result = new ProductService().updateProduct(p);
		System.out.println(result);
		if (result > 0) {

			new ProductMenu().displaySuccess("업데이트성공.");
		} else {
			new ProductMenu().displayError("업데이트실패.");
		}

	}

	public void delectproduct(String id) {
		int result;

		result = new ProductService().delectproduct(id);
		System.out.println(result);
		if (result > 0) {

			new ProductMenu().displaySuccess("삭제성공.");
		} else {
			new ProductMenu().displayError("삭제실패.");
		}

	}

	public void productcheck(String proName) {
		ProductMenu p = new ProductMenu();
		Product m;

		m = new ProductService().productcheck(proName);

		if (m != null) {

			p.displayProduct(m);
		} else {
			p.displayError(proName + "에 해당하는 데이터가 없습니다.");
		}

	}

	public void subselectAll() {

		ProductMenu m = new ProductMenu();
		ArrayList<ProductIO> list = new ProductService().subselectAll();

		if (!list.isEmpty()) {
			m.displayProductList2(list);

		} else {
			m.displayError("해당하는 데이터가 없습니다.");
		}
	}

	public void selectenter() {

		ProductMenu m = new ProductMenu();
		ArrayList<ProductIO> list = new ProductService().selectenter();

		if (!list.isEmpty()) {
			m.displayProductList2(list);

		} else {
			m.displayError("해당하는 데이터가 없습니다.");
		}
	}

	public void selectrelese() {
		ProductMenu m = new ProductMenu();
		ArrayList<ProductIO> list = new ProductService().selectrelese();

		if (!list.isEmpty()) {
			m.displayProductList2(list);

		} else {
			m.displayError("해당하는 데이터가 없습니다.");

		}
	}

	public void insertproducte(ProductIO p) {

		int result;

		result = new ProductService().insertproducte(p);
		System.out.println(result);
		if (result > 0) {

			new ProductMenu().displaySuccess("입고했습니다.");
		} else {
			new ProductMenu().displayError("입고에 실패했습니다..");
		}
	}

	

	public void releseproducte(ProductIO p) {
		int result;

		result = new ProductService().releseproducte(p);
		System.out.println(result);
		if (result > 0) {

			new ProductMenu().displaySuccess("출고했습니다.");
		} else {
			new ProductMenu().displayError("출고에 실패했습니다..");
		}
		
	}

		
	}

