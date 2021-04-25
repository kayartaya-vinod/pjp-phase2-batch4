package com.sapient.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sapient.entity.Product;
import com.sapient.utils.DbUtil;

public class ProductsDao {

	// CRUD operations

	public void create(Product product) {
	}

	public Product getById(Integer id) {
		String sql = "select * from products where id=?";
		
		try(
			Connection conn = DbUtil.createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return resultSetToProduct(rs);
				}
			}
		} // conn, stmt, rs get automatically closed here
		catch(Exception ex) {
			ex.printStackTrace();
			// exception ducking; bad practice 
			// need to throw a custom/user-defined layer specific exception - ex: DaoException 
		}
		
		return null;
	}

	public void update(Product product) {

	}

	public void delete(Integer id) {

	}

	// QUERY operations
	public List<Product> getAll() {
		String sql = "select * from products";
		List<Product> list = new ArrayList<>();
		
		try(
			Connection conn = DbUtil.createConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()) {
				list.add(resultSetToProduct(rs));
			}
		} // conn, stmt, rs get automatically closed here
		catch(Exception ex) {
			ex.printStackTrace();
			// exception ducking; bad practice 
			// need to throw a custom/user-defined layer specific exception - ex: DaoException 
		}
		
		return list;
	}

	Product resultSetToProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setBrand(rs.getString("brand"));
		p.setCategory(rs.getString("category"));
		p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
		p.setPicture(rs.getString("picture"));
		p.setDescription(rs.getString("description"));
		p.setUnitPrice(rs.getDouble("unit_price"));
		p.setDiscount(rs.getDouble("discount"));
		return p;
	}

	public List<Product> getByBrand(String brand) {
		String sql = "select * from products where upper(brand)=upper(?)";
		List<Product> list = new ArrayList<>();
		
		try(
			Connection conn = DbUtil.createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			stmt.setString(1, brand);
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					list.add(resultSetToProduct(rs));
				}
			}
		} // conn, stmt, rs get automatically closed here
		catch(Exception ex) {
			ex.printStackTrace();
			// exception ducking; bad practice 
			// need to throw a custom/user-defined layer specific exception - ex: DaoException 
		}
		
		return list;
	}

	public List<Product> getByCategory(String category) {
		return null;
	}

	public List<Product> getByPriceRange(Double min, Double max) {
		return null;
	}
	
	public static void main(String[] args) {
		
		ProductsDao dao = new ProductsDao();
		List<Product> list = dao.getAll();
		for(Product p: list) {
			System.out.println(p);
		}
	}
}
