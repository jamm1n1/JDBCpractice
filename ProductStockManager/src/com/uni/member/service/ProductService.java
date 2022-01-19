package com.uni.member.service;

import static com.uni.common.PTemplate.commit;
import static com.uni.common.PTemplate.rollback;
import static com.uni.common.PTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.member.dao.ProductDao;
import com.uni.member.dto.Product;
import com.uni.member.dto.ProductIO;

public class ProductService {

	public ArrayList<Product> selectAll() {
		Connection conn = getConnection();

		ArrayList<Product> list = new ProductDao().selectAll(conn);
		return list;

	}

	public int insertproduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().insertproduct(conn, p);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	public int updateProduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProduct(conn, p);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public int delectproduct(String id) {
		Connection conn = getConnection();
		int result = new ProductDao().delectProduct(conn, id);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	public Product productcheck(String proName) {
		Connection conn = getConnection();
		Product m = new ProductDao().productcheck(conn, proName);
		return m;

	}

	public ArrayList<ProductIO> subselectAll() {
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductDao().subselectAll(conn);
		return list;

	}

	public ArrayList<ProductIO> selectenter() {
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductDao().selectenter(conn);
		return list;
		
	}

	public ArrayList<ProductIO> selectrelese() {
		
		Connection conn = getConnection();

		ArrayList<ProductIO> list = new ProductDao().selectrelese(conn);
		return list;
	}

	public int insertproducte(ProductIO p) {
		Connection conn = getConnection();
		int result = new ProductDao().insertproducte(conn, p);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	
	

	public int releseproducte(ProductIO p) {
		Connection conn = getConnection();
		int result = new ProductDao().releseproducte(conn, p);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}
	}


