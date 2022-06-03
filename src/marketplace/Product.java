package marketplace;

/**
 *
 * An abstract class and the superclass of Gadget Class
 * 
 */

public abstract class Product {
	private String brand;
	private String model;
	private float price;
	private String condition;
	private String classification;

	public Product(String brand, String model, float price, String condition, String classification) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.condition = condition;
		this.classification = classification;
	}

	/* --------> GETTERS <-------- */

	public String getName() {
		return this.brand + " " + this.model;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}

	public float getPrice() {
		return this.price;
	}

	public String getCondition() {
		return this.condition;
	}

	public String getClassification() {
		return this.classification;
	}

	/* Used to show the full information of a product */
	public void viewInfo() {
		System.out.println("\tBrand: " + this.getBrand());
		System.out.println("\tModel: " + this.getModel());
		System.out.println("\tPrice: " + this.getPrice());
		System.out.println("\tCondition: " + this.getCondition());
		System.out.println("\tClassification: " + this.getClassification());
	}
}