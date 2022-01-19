package com.uni.member.dao;




import static com.uni.common.PTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.common.PTemplate;
import com.uni.member.dto.Product;
import com.uni.member.dto.ProductIO;







public class ProductDao {
	
	private Properties prop = null;
	
	public ProductDao() {
		 
		 
		
		 try {
			 prop = new Properties();
			prop.load(new FileReader("resourse/query.properties"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
	 }
	public ArrayList<Product> selectAll(Connection conn) {
		ArrayList<Product> list =null;
		
		 
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
		
		
		
		try {
	        
	         new PTemplate();
			conn = PTemplate.getConnection();
	         
	         System.out.println("conn = "+conn ); 
	         
	         
	         stmt = conn.createStatement();
	         
	        
	         rset = stmt.executeQuery(sql);
	         
	         list = new ArrayList<Product>();
	         while(rset.next()) {
	        	 Product p = new Product();
	      
	        	 
	        	 p.setProductId(rset.getString("PRODUCT_ID"));
	        	 p.setpName(rset.getString("P_NAME"));
	        	 p.setPrice(rset.getInt("PRICE"));
	        	 p.setDescription(rset.getString("DESCRIPTION"));
	        	 p.setStock(rset.getInt("STOCK"));
	        	 list.add(p);
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  close(rset);
			  close(stmt);
	    	  
	      }
		return list;
	}
	public int insertproduct(Connection conn, Product p) {
		int result = 0;
		
		PreparedStatement pstmt = null; 
				
		String sql = prop.getProperty("insertProduct");

        try {
		
        pstmt = conn.prepareStatement(sql);
                
        pstmt.setString(1, p.getProductId());
        pstmt.setString(2, p.getpName());
        pstmt.setInt(3, p.getPrice());
        pstmt.setString(4, p.getDescription());
        pstmt.setInt(5, p.getStock());
        
        result = pstmt.executeUpdate();
        
          
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}finally {
			
				close(pstmt);
						
		}
		return result;
		
	
}
	public int updateProduct(Connection conn, Product p) {
		int result = 0;
		
	     
		PreparedStatement pstmt = null; 
		
		
		String sql = prop.getProperty("updateProduct");
	
        try {
			
        
        
       
        pstmt = conn.prepareStatement(sql);
      
        
        pstmt.setString(1,p.getProductId()); 
        pstmt.setString(2,p.getpName() );
        pstmt.setInt(3,p.getPrice() );
        pstmt.setString(4,p.getDescription() );
        pstmt.setInt(5,p.getStock() );
        
        result = pstmt.executeUpdate();
       
        
       
         
		}catch (Exception e) {
			
			e.printStackTrace();
		
		}finally {
			
				close(pstmt);  
		
			
		}
		return result;	
		
	}
	public int delectProduct(Connection conn, String id) {
		int result = 0;
		 
	      
	      PreparedStatement pstmt = null; 
	      		 			
	      
	      String sql = prop.getProperty("deleteProduct");
	      
	      System.out.println(sql);
		
		try {
	        
	         pstmt = conn.prepareStatement(sql);	               
	         
	         
	         pstmt.setString(1, id);
	         result = pstmt.executeUpdate();
	         		      
	         
	 		}catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			
	 				close(pstmt);
	 			
	 			
	 		}
	 		return result;	
	 		
	 	}

	public Product productcheck(Connection conn, String proName) {
		Product p = null;
		
	      
	      PreparedStatement pstmt = null; 
	      
	      ResultSet rset = null; 
	      
	      String sql = prop.getProperty("CheckProduct");
	      try {
	    	  pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, proName);
		         rset = pstmt.executeQuery();
		         while(rset.next()) {
		        	 p = new Product();
		      
		        	 
		        	 p.setProductId(rset.getString("PRODUCT_ID"));
		        	 p.setpName(rset.getString("P_NAME"));
		        	 p.setPrice(rset.getInt("PRICE"));
		        	 p.setDescription(rset.getString("DESCRIPTION"));
		        	 p.setStock(rset.getInt("STOCK"));
		        	
		         }
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		    	  close(rset);
				  close(pstmt);
		    	  
		      }
			return p;
	}
	public ArrayList<ProductIO> subselectAll(Connection conn) {
		ArrayList<ProductIO> list =null;
		
		 
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("subselectAll");
		
		
		
		try {
	        
	         new PTemplate();
			conn = PTemplate.getConnection();
	         
	         System.out.println("conn = "+conn ); 
	         
	         
	         stmt = conn.createStatement();
	         
	        
	         rset = stmt.executeQuery(sql);
	         
	         list = new ArrayList<ProductIO>();
	         while(rset.next()) {
	        	 ProductIO p = new ProductIO();
	             
	        	 
	        	 p.setIoNum(rset.getInt("IO_NUM"));
	        	 p.setProductId(rset.getString("PRODUCT_ID"));
	        	 p.setpName(rset.getString("P_NAME"));
	        	 p.setIoDate(rset.getDate("IO_DATE"));
	        	 p.setAmount(rset.getInt("AMOUNT"));
	        	 p.setStatus(rset.getString("STATUS"));
	        	 list.add(p);	        	 
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  close(rset);
			  close(stmt);
	    	  
	      }
		return list;
	
	
}
	public ArrayList<ProductIO> selectenter(Connection conn) {
		ArrayList<ProductIO> list =null;
		
		 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectenter");
		
		
		
		try {
	        
	         new PTemplate();
			conn = PTemplate.getConnection();
	         
	         System.out.println("conn = "+conn ); 
	         
	         
	         pstmt = conn.prepareStatement(sql);
	         
	        
	         rset = pstmt.executeQuery();
	         
	         list = new ArrayList<ProductIO>();
	         while(rset.next()) {
	        	 ProductIO p = new ProductIO();
	             
	        	 
	        	 p.setIoNum(rset.getInt("IO_NUM"));
	        	 p.setProductId(rset.getString("PRODUCT_ID"));
	        	 p.setpName(rset.getString("P_NAME"));
	        	 p.setIoDate(rset.getDate("IO_DATE"));
	        	 p.setAmount(rset.getInt("AMOUNT"));
	        	 p.setStatus(rset.getString("STATUS"));
	        	 list.add(p);	        	 
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  close(rset);
			  close(pstmt);
	    	  
	      }
		return list;
		
	}
	public ArrayList<ProductIO> selectrelese(Connection conn) {
		ArrayList<ProductIO> list =null;
		
		 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectrelese");
		
		
		
		try {
	        
	         new PTemplate();
			conn = PTemplate.getConnection();
	         
	         System.out.println("conn = "+conn ); 
	         
	         
	         pstmt = conn.prepareStatement(sql);
	         
	        
	         rset = pstmt.executeQuery();
	         
	         list = new ArrayList<ProductIO>();
	         while(rset.next()) {
	        	 ProductIO p = new ProductIO();
	             
	        	 
	        	 p.setIoNum(rset.getInt("IO_NUM"));
	        	 p.setProductId(rset.getString("PRODUCT_ID"));
	        	 p.setpName(rset.getString("P_NAME"));
	        	 p.setIoDate(rset.getDate("IO_DATE"));
	        	 p.setAmount(rset.getInt("AMOUNT"));
	        	 p.setStatus(rset.getString("STATUS"));
	        	 list.add(p);	        	 
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	    	  close(rset);
			  close(pstmt);
	    	  
	      }
		return list;
	}
	public int insertproducte(Connection conn, ProductIO p) {
		
		int result = 0;
		
	     
		PreparedStatement pstmt = null; 
		
		
		String sql = prop.getProperty("insertproducte");
	
        try {
			
        
        
       
        pstmt = conn.prepareStatement(sql);
      
        
       
        pstmt.setString(1,p.getProductId() );       
       
        pstmt.setInt(2,p.getAmount() );
        
        
        result = pstmt.executeUpdate();
       
        
       
         
		}catch (Exception e) {
			
			e.printStackTrace();
		
		}finally {
			
				close(pstmt);  
		
			
		}
		return result;	
	}
	public int releseproducte(Connection conn, ProductIO p) {
		int result = 0;
		
	     
		PreparedStatement pstmt = null; 
		
		
		String sql = prop.getProperty("releseproducte");
	
        try {
   
        pstmt = conn.prepareStatement(sql);
  
        pstmt.setString(1,p.getProductId() );       
       
        pstmt.setInt(2,p.getAmount() );
        
        
        result = pstmt.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e) {
        	System.out.println("재고수량이 부족합니다");
        
       
         
		}catch (Exception e) {
			
			e.printStackTrace();
		
			
		
		}finally {
			
				close(pstmt);  
		
			
		}
		return result;	
	}
}

		
	

