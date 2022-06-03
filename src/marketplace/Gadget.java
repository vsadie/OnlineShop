package marketplace;

/**
 *
 * Subclass of Product Class
 * 
 */

public class Gadget extends Product {

	// CONDITIONS
	public static final String COND_LIKE_NEW = "like-new";
	public static final String COND_GOOD = "good";
	public static final String COND_MINT = "mint";

	// CLASSIFICATIONS
	public static final String CLASS_CAMERA = "camera";
	public static final String CLASS_MOBILE = "mobile";
	public static final String CLASS_COMPUTER = "computer";

	public Gadget(String brand, String model, float price, String condition, String classification) {
		super(brand, model, price, condition, classification);
	}

}
