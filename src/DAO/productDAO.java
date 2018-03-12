package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connect.DBConnect;
import entity.product;
import junit.framework.TestCase;

public class productDAO extends TestCase {
	private Connection con;

	public productDAO() {

	}

	public boolean openConnection() {
		con = DBConnect.getConnection();
		return (con != null) ? true : false;
	}

	public boolean openConnection(String port) {
		con = DBConnect.getConnection(port);
		return (con != null) ? false : true;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("Error Connect.");
		}
	}

	public boolean insertProduct(product product) {
		if (product.getProductID().isEmpty() || product.getProductName().isEmpty()) {
			return false;
		} else if (product.getPriceImp() > product.getPriceExp()) {
			return false;
		} else if (product.getPriceImp() <= 0 || product.getPriceExp() <= 0) {
			return false;
		} else if (product.getPriceImp() == product.getPriceExp()) {
			return false;
		} else if (product.getAmount() <= 0) {
			return false;
		} else {
			openConnection();
			boolean result = false;
			String sqlInsert = "INSERT INTO product VALUES(?,?,?,?,?,?)";
			try {
				PreparedStatement preStatement = con.prepareStatement(sqlInsert);
				preStatement.setString(1, product.getProductID());
				preStatement.setInt(2, product.getCategoryID());
				preStatement.setString(3, product.getProductName());
				preStatement.setFloat(4, product.getPriceImp());
				preStatement.setFloat(5, product.getPriceExp());
				preStatement.setInt(6, product.getAmount());
				preStatement.execute();
				result = true;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error.");
			} finally {
				closeConnection();
			}
			return result;
		}
	}

	public boolean updateProduct(product Product) {
		if (Product.getProductID().isEmpty() || Product.getProductName().isEmpty()) {
			return false;
		} else if (Product.getPriceImp() > Product.getPriceExp()) {
			return false;
		} else {
			openConnection();
			boolean result = false;
			String sqlUpdate = "update product set category_id=?,product_name=?,price_import=?,price_export=?,amount=? where product_id=?";
			try {
				PreparedStatement preStatement = con.prepareStatement(sqlUpdate);
				preStatement.setInt(1, Product.getCategoryID());
				preStatement.setString(2, Product.getProductName());
				preStatement.setFloat(3, Product.getPriceImp());
				preStatement.setFloat(4, Product.getPriceExp());
				preStatement.setInt(5, Product.getAmount());
				preStatement.setString(6, Product.getProductID());
				preStatement.executeUpdate(sqlUpdate);
				result = true;
			} catch (Exception e) {
				System.out.println("Error.");
			} finally {
				closeConnection();
			}
			return result;
		}
	}

	public ArrayList<product> getListProduct() {
		openConnection();
		String sql = "SELECT * FROM product";
		ArrayList<product> lstBook = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement preStatement = con.prepareStatement(sql);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				String productID = rs.getString("product_id");
				int categoryID = rs.getInt("category_id");
				String productName = rs.getString("product_name");
				Float priceImp = rs.getFloat("price_import");
				Float priceExp = rs.getFloat("price_export");
				int amount = rs.getInt("amount");
				product pr = new product(productID, categoryID, productName, priceImp, priceExp, amount);
				lstBook.add(pr);
			}
		} catch (Exception e) {
			System.out.println("Error.");
		} finally {
			closeConnection();
		}
		return lstBook;
	}

	public boolean deleteProduct_ID(String productID) {
		openConnection();
		boolean result = false;
		String sqldelete = "DELETE FROM product WHERE product_id=?";
		try {
			PreparedStatement preStatement = con.prepareStatement(sqldelete);
			preStatement.setString(1, productID);
			preStatement.execute();
			result = true;
		} catch (Exception e) {
			System.out.println("Error.");
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean deleteAllProduct() {
		openConnection();
		boolean result = false;
		String sqldeleteAll = "DELETE FROM product";
		try {
			PreparedStatement preStatement = con.prepareStatement(sqldeleteAll);
			preStatement.execute();
			result = true;
		} catch (Exception e) {
			System.out.println("Lỗi.");
		} finally {
			closeConnection();
		}
		return result;
	}
}
