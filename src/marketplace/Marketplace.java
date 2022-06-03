package marketplace;

import java.util.ArrayList;

/**
 *
 * Subclass of Shop Class
 * 
 */

public class Marketplace extends Shop {

	public Marketplace(String name) {
		super(name);
	}

	@Override
	public void add(Product item) {
		items.add(item);
		remainingStocks++;
	}

	@Override
	public void remove(Product item) {

		items.remove(item);
		remainingStocks--;

	}

	@Override
	public void viewProducts() {

		System.out.println();
		System.out.println(this.getName() + " Product List:\n");
		for (Product stock : items) {
			System.out.println("Item " + (items.indexOf(stock) + 1) + ":");
			System.out.println("Brand: " + stock.getBrand());
			System.out.println("Model: " + stock.getModel());
			System.out.println("Price: " + stock.getPrice());
			System.out.println("Condition: " + stock.getCondition());
			System.out.println("Classification: " + stock.getClassification());
			System.out.println(); // new line
		}

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ArrayList<Product> getItems() {
		return this.items;
	}

	/* Displays a welcome message */
	public void welcomeMessage() {
		System.out.println("\n=================[ MARKETPLACE ]=================");
		System.out.println("\nWelcome to " + this.getName().toUpperCase() + ", where various");
		System.out.println("kinds of high-quality gadgets are available!!!");
		System.out.println("NOTE: e-Coins is the only way to pay in this shop");
		System.out.println("\n=================================================\n");

	}

}