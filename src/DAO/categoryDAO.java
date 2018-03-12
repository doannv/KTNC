package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connect.DBConnect;
import entity.category;
import junit.framework.TestCase;

public class categoryDAO extends TestCase {
	private Connection con;

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

	// them loai SP va tra ve true neu them thanh cong vao CSDL
	public boolean insertCategory(category category) {
		if (category.getCategoryName().isEmpty()) {
			return false;
		} else {
			openConnection();
			boolean result = false;
			String sqlInsert = "insert into category values(?,?)";
			try {
				PreparedStatement preStatement = con.prepareStatement(sqlInsert);
				preStatement.setInt(1, category.getCategoryID());
				preStatement.setString(2, category.getCategoryName());
				preStatement.executeQuery();
				result = true;
			} catch (Exception e) {
				System.out.println("Error");
			} finally {
				closeConnection();
			}
			return result;
		}
	}

	public boolean updateCategory(category Category) {
		if (Category.getCategoryName().isEmpty()) {
			return false;
		} else {
			openConnection();
			boolean result = false;
			String sqlUpdate = "update category set category_name=? where category_id=?";
			try {
				PreparedStatement preStatement = con.prepareStatement(sqlUpdate);
				preStatement.setString(1, Category.getCategoryName());
				preStatement.setInt(2, Category.getCategoryID());
				preStatement.executeUpdate(sqlUpdate);				
				result = true;
			} catch (Exception e) {
				System.out.println("Error");
			} finally {
				closeConnection();
			}
			return result;
		}
	}

	// lay danh sach the loai SP
	public ArrayList<category> getListCategory() {
		openConnection();
		String sqlSelect = "Select*from category";
		ArrayList<category> lstTheloai = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			PreparedStatement preStatement = con.prepareStatement(sqlSelect);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				int categoryID = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
				category ca = new category(categoryID, categoryName);
				lstTheloai.add(ca);
			}
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
			closeConnection();
		}
		return lstTheloai;
	}

	public boolean deleteCategory_ID(int category_id) {
		openConnection();
		boolean result = false;
		String sqlDelete = "Delete from category where category_id=?";
		try {
			PreparedStatement preStatement = con.prepareStatement(sqlDelete);
			preStatement.setInt(1, category_id);
			preStatement.execute();
			result = true;
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean deleteAllCategory() {
		openConnection();
		boolean result = false;
		String sqlDeleteAll = "DELETE FROM category";
		try {
			PreparedStatement preStatement = con.prepareStatement(sqlDeleteAll);
			preStatement.executeQuery();
			result = true;
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
			closeConnection();
		}
		return result;
	}
}
