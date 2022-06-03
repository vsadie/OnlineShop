package customer;

import java.util.ArrayList;

import marketplace.*;

/**
 *
 * Holds different methods that an actual customer can do while shopping
 * 
 * 
 */

public class Customer {
	private String name;
	private String username;
	private String address;
	private String number;
	private float coins;
	private ArrayList<Product> cart;

	// constructor1
	public Customer() {
		cart = new ArrayList<Product>();
	}

	// constructor2
	public Customer(String name, String username, String address, String number) {
		this.name = name;
		this.username = username;
		this.address = address;
		this.number = number;
		cart = new ArrayList<Product>();
	}

	/* Checks if the customer can afford all the items in cart */
	protected boolean canAfford(ArrayList<Product> cart) {
		int totalCoins = 0;
		for (Product item : cart) {
			totalCoins += item.getPrice();
		}

		if (this.getCoins() >= totalCoins)
			return true;
		else
			return false;
	}

	/* Adds item to the cart */
	public void addToCart(Product item) {
		cart.add(item);
	}

	/* Attempts to buy the item */
	public void checkOut(Marketplace marketplace) {
		float totalPayment = 0;
		System.out.println();

		if (!cart.isEmpty()) {
			// NOTE
			for (Product item : cart) {
				System.out.println("**LOG: Customer " + this.name + " is trying to buy " + item.getName() + " from "
						+ marketplace.getName() + ".");
			}
			if (canAfford(cart)) {
				for (Product item : cart) {
					totalPayment += item.getPrice();
					marketplace.remove(item); // buy an item
					this.coins -= item.getPrice(); // deduct coins

					System.out.println("**LOG: The item " + item.getName() + " was successfully bought by customer "
							+ this.getName() + ".");
				}
				System.out.println("\nTOTAL PAYMENT: " + totalPayment);
				cart.clear();
			} else {
				for (Product item : cart) {
					System.out.println(
							"**LOG: " + item.getName() + " cannot be purchased because of insufficient funds.");
				}
			}
		} else {
			System.out.println("**LOG: Customer hasn't added any item to the cart.");
		}

	}

	/* Displays all the items added to cart */
	public void viewCart() {
		System.out.println("\n==========[ CUSTOMER STATE ]==========");
		System.out.println("\tName: " + this.name);
		System.out.println("\tAddress: " + this.address);
		System.out.println("\tMobile Number: " + this.number);
		System.out.println("\te-Coins: " + this.coins);
		System.out.println("\n\tCart: ");

		if (!this.cart.isEmpty()) {
			for (Product item : cart) {
				System.out.println("\n\tItem " + (cart.indexOf(item) + 1) + ": ");
				item.viewInfo();
			}

			float total = 0;
			for (Product itemCart : cart) {
				total += itemCart.getPrice();
			}

			System.out.println("\nTOTAL: " + total);

		} else
			System.out.println("\tCustomer hasn't added any item to the cart.");

		System.out.println("======================================");
	}

	/* --------> SETTERS and GETTERS <-------- */

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getCoins() {
		return coins;
	}

	public void setCoins(float coins) {
		this.coins = coins;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

}