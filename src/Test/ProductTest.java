package Test;

import java.util.ArrayList;

import DAO.categoryDAO;
import DAO.productDAO;
import entity.category;
import entity.product;
import junit.framework.TestCase;

public class ProductTest extends TestCase {
	private categoryDAO CategoryDAO;
	private category Category;
	private productDAO ProductDAO;
	private product Product;

	public void setUp() {
		CategoryDAO = new categoryDAO();
		Category = new category();
		ProductDAO = new productDAO();
		Product = new product();
	}

	/**
	 * Test kết nối thành công
	 */
	public void testConnectionSuccess() {
		assertEquals(true, CategoryDAO.openConnection());
	}

	public void testConnectionSuccess1() {
		assertEquals(true, ProductDAO.openConnection()); // Kiểm tra kết nối.
															// Kết quả mong muốn
															// là true
	}

	/**
	 * Test kết nối lỗi
	 */
	// public void testConnectionFail() {
	// assertEquals(false,CategoryDAO.openConnection("1222")); // Kiểm tra kết
	// nối lỗi. Kết quả mong muốn là false
	// }
	// public void testConnectionFail1() {
	// assertEquals(false,ProductDAO.openConnection("1222")); // Kiểm tra kết
	// nối lỗi. Kết quả mong muốn là false
	// }

	public void testDeleteAllProduct() {
		ProductDAO.deleteAllProduct();
		assertEquals(0, ProductDAO.getListProduct().size());
	}

	// test insert thanh cong
	public void testInsertProductSuccess() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("Product1");
		Product.setCategoryID(1);
		Product.setProductName("Iphone 4");
		Product.setPriceImp(1000);
		Product.setPriceExp(2000);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		product pr = ProductDAO.getListProduct().get(0);
		assertEquals(true, pr.toString().equals(pr.toString()));
		ProductDAO.deleteProduct_ID("Product1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test insert thieu ma SP
	public void testInsertProductNullID() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("");
		Product.setCategoryID(1);
		Product.setProductName("Iphone 4");
		Product.setPriceImp(1000);
		Product.setPriceExp(2000);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		CategoryDAO.deleteCategory_ID(1);
		ProductDAO.deleteProduct_ID("Product1");
	}

	// test insert thiếu tên SP
	public void testInsertProductNullName() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("Product1");
		Product.setCategoryID(1);
		Product.setProductName("");
		Product.setPriceImp(1000);
		Product.setPriceExp(2000);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		CategoryDAO.deleteCategory_ID(1);
		ProductDAO.deleteProduct_ID("Product1");
	}

	// test insert thieu mã SP, tên SP
	public void testInsertProductNULLALL() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("");
		Product.setCategoryID(1);
		Product.setProductName("");
		Product.setPriceImp(1000);
		Product.setPriceExp(2000);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		CategoryDAO.deleteCategory_ID(1);
	}

	// test insert khác mã loại
	public void testInsertProductIDFKNotExist() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(2);
		Product.setProductName("Samsung");
		Product.setPriceImp(1000);
		Product.setPriceExp(2000);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1 & 2);
	}

	// test gia nhap cao hon gia ban
	public void testInsertProductPriceIP() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(1000);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);

	}

	// test gia nhap <0
	public void testInsertProductPriceIP0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(-1);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteAllCategory();
	}

	// test gia nhap = 0
	public void testInsertProductPriceIP_0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(0);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteAllCategory();
	}

	// test gia nhap > 0
	public void testInsertProductPriceIP_1() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(1);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(1, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteAllCategory();
	}

	// test gia ban < 0
	public void testInsertProductPriceEX_0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(500);
		Product.setPriceExp(-1);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteAllCategory();
	}

	// test gia ban = 0
	public void testInsertProductPriceEXbang0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(500);
		Product.setPriceExp(0);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteAllCategory();
	}

	// test gia ban > 0
	public void testInsertProductPriceEXlon0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(500);
		Product.setPriceExp(1);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test gia ban = giá nhập
	public void testInsertProductPriceEX_IP() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(1);
		Product.setPriceExp(1);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test số lượng < 0
	public void testInsertProductAmount_0() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(-1);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test số lượng = 0
	public void testInsertProductAmount_1() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(0);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test số lượng > 0
	public void testInsertProductAmount_2() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(1);
		ProductDAO.insertProduct(Product);
		ArrayList<product> ListProduct = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(1, ListProduct.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update success các trường tên sp, giá nhập, giá xuất, số lượng
	public void testUpdateProductSuccess() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung galaxy");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.updateProduct(Product);

		Product.setProductID("SP2");
		Product.setCategoryID(1);
		Product.setProductName("Iphone 4s");
		Product.setPriceImp(500);
		Product.setPriceExp(1000);
		Product.setAmount(20);
		ProductDAO.updateProduct(Product);

		ArrayList<product> productList = ProductDAO.getListProduct();
		assertEquals(true, ProductDAO.insertProduct(Product));
		assertEquals(0, productList.size());
		ProductDAO.deleteAllProduct();
		CategoryDAO.deleteCategory_ID(1);
	}

	// test update khi thiếu trường tên sp
	public void testUpdateProductNullName() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung galaxy");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(10);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("");
		Product.setPriceImp(500);
		Product.setPriceExp(600);
		Product.setAmount(20);
		ProductDAO.updateProduct(Product);
		ProductDAO.deleteProduct_ID("SP1");
		ArrayList<product> productList = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, productList.size());
		CategoryDAO.deleteCategory_ID(1);
		ProductDAO.deleteProduct_ID("SP1");
	}

	// test update khi thiếu trường mã sp
	public void testUpdateProductNullID() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung galaxy");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(10);

		Product.setProductID("");
		Product.setCategoryID(1);
		Product.setProductName("Iphone 5s");
		Product.setPriceImp(500);
		Product.setPriceExp(600);
		Product.setAmount(20);
		ProductDAO.updateProduct(Product);
		ProductDAO.deleteProduct_ID("SP1");
		ArrayList<product> productList = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, productList.size());
		CategoryDAO.deleteCategory_ID(1);
		ProductDAO.deleteProduct_ID("SP1");
	}

	// test update khi thiếu trường mã sp và tên sp
	public void testUpdateProductNullNameAndID() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung galaxy");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(10);

		Product.setProductID("");
		Product.setCategoryID(1);
		Product.setProductName("");
		Product.setPriceImp(500);
		Product.setPriceExp(600);
		Product.setAmount(20);
		ProductDAO.updateProduct(Product);
		ProductDAO.deleteProduct_ID("SP1");
		ArrayList<product> productList = ProductDAO.getListProduct();
		assertEquals(false, ProductDAO.insertProduct(Product));
		assertEquals(0, productList.size());
		CategoryDAO.deleteCategory_ID(1);
		ProductDAO.deleteProduct_ID("SP1");
	}

	// test xoa SP
	public void testDeleteProduct() {
		CategoryDAO.deleteAllCategory();
		Category.setCategoryID(1);
		Category.setCategoryName("Iphone");
		CategoryDAO.insertCategory(Category);

		Product.setProductID("SP1");
		Product.setCategoryID(1);
		Product.setProductName("Samsung galaxy");
		Product.setPriceImp(200);
		Product.setPriceExp(500);
		Product.setAmount(10);
		ProductDAO.insertProduct(Product);

		ArrayList<product> ProductList = ProductDAO.getListProduct();
		assertEquals(1, ProductList.size());
		ProductDAO.deleteProduct_ID("SP1");
		CategoryDAO.deleteCategory_ID(1);
	}

}
