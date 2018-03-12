package entity;

public class category {
	private int categoryID;
	private String categoryName="";
	public category() {
		super();
	}
	public category(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "category [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
}
