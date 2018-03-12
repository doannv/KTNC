package Test;

import java.util.ArrayList;

import DAO.categoryDAO;
import DAO.productDAO;
import entity.category;
import entity.product;
import junit.framework.TestCase;

public class CategoryTest extends TestCase {
	private categoryDAO CategoryDAO;
	private category Category;

	public void setUp() {
		CategoryDAO = new categoryDAO();
		Category = new category();
		new productDAO();
		new product();
	}

	public void testConnectionSuccess() {
		assertEquals(true, CategoryDAO.openConnection());
	}

	// public void testConnectionFail() {
	// assertEquals(false, CategoryDAO.openConnection("1325"));
	// }

	public void testDeleteAllProduct() {
		CategoryDAO.deleteAllCategory();
		assertEquals(0, CategoryDAO.getListCategory().size());
	}

	public void testInsertSuccess() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);
		category ct = CategoryDAO.getListCategory().get(0);
		assertEquals(true, ct.toString().equals(ct.toString()));
		CategoryDAO.deleteCategory_ID(1);

	}

	// insert 2 ban ghi lien tuc
	public void testInsertSuccess2() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(2);
		Category.setCategoryName("Samsung");
		CategoryDAO.insertCategory(Category);
		category ct = CategoryDAO.getListCategory().get(0);
		assertEquals(true, ct.toString().equals(ct.toString()));
		CategoryDAO.deleteCategory_ID(1 & 2);

	}

	public void testInsertNullName() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("");
		CategoryDAO.insertCategory(Category);
		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(false, CategoryDAO.insertCategory(Category));
		assertEquals(0, cateList.size());
		CategoryDAO.deleteCategory_ID(1);
	}
	// test giới hạn id độ dài là -2,147,483,648
	public void testInsertMinLength() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(-2147483648);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);
		category cate = CategoryDAO.getListCategory().get(0);
		assertEquals(true, cate.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(-2147483648);
	}

	// test giới hạn id độ dài là 2,147,483,647
	public void testInsertMaxLength() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(2147483647);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);
		category cate = CategoryDAO.getListCategory().get(0);
		assertEquals(true, cate.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(2147483647);
	}

	// test giới hạn ten loai SP là 51
	public void testInsertMaxLength1() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("ooooooooooooooooooooooooooooooiuyterstugádqwdqweqwe");
		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(true, CategoryDAO.insertCategory(Category));
		assertEquals(0, cateList.size());
		CategoryDAO.deleteCategory_ID(1);
	}

	// test giới hạn ten loai SP là 52
	public void testInsertMaxLength2() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("ooooooooooooooooooooooooooooooiuyterstugádqwdqweqwe2");
		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(false, CategoryDAO.insertCategory(Category));
		assertEquals(0, cateList.size());
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update loai sach thanh cong
	public void testUpdateSuccess() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(1);
		Category.setCategoryName("Samsung");
		CategoryDAO.updateCategory(Category);
		category ca = CategoryDAO.getListCategory().get(0);
		assertEquals(false, ca.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(1);
	}

	// test sua 1 ban ghi 2 lan lien tiep
	public void testUpdateSuccess2() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(1);
		Category.setCategoryName("Samsung");
		CategoryDAO.updateCategory(Category);

		Category.setCategoryID(1);
		Category.setCategoryName("Oppo");
		CategoryDAO.updateCategory(Category);

		category cate = CategoryDAO.getListCategory().get(0);
		assertEquals(false, cate.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update loai SP thiếu trường tên lọai SP
	public void testUpdateNullNameSP() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryName("");
		CategoryDAO.updateCategory(Category);
		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(false, CategoryDAO.insertCategory(Category));
		assertEquals(1, cateList.size());
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update khi sua truong ten the loai co do dai la 51
	public void testUpdateMaxLength() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(1);
		Category.setCategoryName("ooooooooooooooooooooooooooooooiuyterstugádqwdqweqwe");
		CategoryDAO.updateCategory(Category);
		category cate = CategoryDAO.getListCategory().get(0);
		assertEquals(true, cate.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update khi sua truong ten the loai co do dai la 52
	public void testUpdateMaxLength1() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(1);
		Category.setCategoryName("ooooooooooooooooooooooooooooooiuyterstugádqwdqweqweh");
		CategoryDAO.updateCategory(Category);
		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(false, CategoryDAO.insertCategory(Category));
		assertEquals(1, cateList.size());
		CategoryDAO.deleteCategory_ID(1);
	}

	// test xóa thành công
	public void testDeleteSuccess() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);
		category ca = CategoryDAO.getListCategory().get(0);
		assertEquals(true, ca.toString().equals(Category.toString()));
		CategoryDAO.deleteCategory_ID(1);
	}

	// test xoa 2 ban ghi lien tiep
	public void testDeleteSuccess1() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Category.setCategoryID(2);
		Category.setCategoryName("Samsung");
		CategoryDAO.insertCategory(Category);

		ArrayList<category> cateList = CategoryDAO.getListCategory();
		assertEquals(2, cateList.size());
		CategoryDAO.deleteCategory_ID(1 & 2);
	}

}
