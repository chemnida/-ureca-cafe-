package com.shop.cafe.dao;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Component;
import com.shop.cafe.dto.Product;

@Component
public class ProductDao {

	public List<Product> getAllProducts() throws Exception {
		//JDBC 6단계

		//1. 드라이버 등록
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/ureca?serverTimezone=UTC";
		String user="ureca";
		String pw="ureca";
		
		String sql = "select * from product";
		
		try(
				//2. 연결
				Connection con=DriverManager.getConnection(url, user, pw);
				
				//3.Statement생성
				PreparedStatement stmt = con.prepareStatement(sql);
				
				//4.SQL전송
				ResultSet rs = stmt.executeQuery(); 
			) {
			
			//5.결과받기
			List<Product> list = new ArrayList();
			while(rs.next()) {
				int prodcode = rs.getInt("prodcode");
				String prodname = rs.getString("prodName");
				String pimg = rs.getString("pimg");
				int price = rs.getInt("price");
				
				list.add(new Product(prodcode, price, prodname, pimg));
				
			}
			return list;
			
		}
		
	}
	
}
