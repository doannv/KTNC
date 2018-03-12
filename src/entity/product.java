package entity;

public class product {
	private String productID="";
	private int categoryID;
	private String productName = "";
	private float priceImp;
	private float priceExp;
	private int amount;

	public product() {

	}

	public product(String productID, int categoryID, String productName, float priceImp, float priceExp, int amount) {
		super();
		this.productID = productID;
		this.categoryID = categoryID;
		this.productName = productName;
		this.priceImp = priceImp;
		this.priceExp = priceExp;
		this.amount = amount;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPriceImp() {
		return priceImp;
	}

	public void setPriceImp(float priceImp) {
		this.priceImp = priceImp;
	}

	public float getPriceExp() {
		return priceExp;
	}

	public void setPriceExp(float priceExp) {
		this.priceExp = priceExp;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "product [productID=" + productID + ", categoryID=" + categoryID + ", productName=" + productName
				+ ", priceImp=" + priceImp + ", priceExp=" + priceExp + ", amount=" + amount + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
