package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import customer.*;
import files.*;
import marketplace.*;

/**
 * 
 * This is an online shop application wherein a user can buy items from the
 * marketplace using e-Coins. All of the Main Principles of OOP were used in
 * building this application. Different Java practices were also applied.
 * 
 */

public class Main {

	// Global Variables
	static Marketplace marketplace;
	static Customer customer;
	static Scanner scanner;
	static ArrayList<Gadget> gadgets; // collection of the items that will be added to the marketplace
	static ArrayList<Customer> customers; // collection of Customer object
	static HashMap<String, String> users; // collection of username and passwords
	static String username, password, name, address, number;

	public static void main(String[] args) throws InterruptedException {

		// initialization
		marketplace = new Marketplace("Techy-Techy Marketplace");
		scanner = new Scanner(System.in);

		// adds product to the marketplace
		addProductsInMarketPlace();

		// shows a short welcome message
		marketplace.welcomeMessage();

		launchShop();

	}

	/* Reads the usernames and passwords from loginCredentials.txt */
	private static void setUsers() {
		try {
			users = LoginCredentials.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/* Reads the customer information from customerInformation.txt */
	private static void setCustomers() {
		try {
			customers = CustomerInformation.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/* Adding products to the marketplace */
	private static void addProductsInMarketPlace() {
		gadgets = new ArrayList<Gadget>(
				Arrays.asList(new Gadget("Nikon", "Z50", 35000, Gadget.COND_LIKE_NEW, Gadget.CLASS_CAMERA),
						new Gadget("Canon", "EOS M6", 15510, Gadget.COND_GOOD, Gadget.CLASS_CAMERA),
						new Gadget("GoPro", "Hero 8", 14500, Gadget.COND_LIKE_NEW, Gadget.CLASS_CAMERA),
						new Gadget("Nikon", "D3", 70000, Gadget.COND_MINT, Gadget.CLASS_CAMERA),
						new Gadget("Fujifilm", "X-A3", 10000, Gadget.COND_MINT, Gadget.CLASS_CAMERA),
						new Gadget("Asus", "ROG Phone II", 16000, Gadget.COND_GOOD, Gadget.CLASS_MOBILE),
						new Gadget("Apple", "iPhone 6", 4500, Gadget.COND_GOOD, Gadget.CLASS_MOBILE),
						new Gadget("Samsung", "Galaxy S10+", 23000, Gadget.COND_MINT, Gadget.CLASS_MOBILE),
						new Gadget("Apple", "iPhone 12 Pro", 47000, Gadget.COND_LIKE_NEW, Gadget.CLASS_MOBILE),
						new Gadget("Realme", "C12", 5400, Gadget.COND_GOOD, Gadget.CLASS_MOBILE),
						new Gadget("Lenovo", "ThinkPad E580", 15990, Gadget.COND_GOOD, Gadget.CLASS_COMPUTER),
						new Gadget("Acer", "Swift 3 Ultrabook", 19900, Gadget.COND_GOOD, Gadget.CLASS_COMPUTER),
						new Gadget("Asus", "X555LAB", 12999, Gadget.COND_GOOD, Gadget.CLASS_COMPUTER),
						new Gadget("Apple", "Macbook Air 2015", 25000, Gadget.COND_GOOD, Gadget.CLASS_COMPUTER),
						new Gadget("Apple", "Macbook Air M1", 48000, Gadget.COND_LIKE_NEW, Gadget.CLASS_COMPUTER)));

		marketplace.getItems().clear(); // clears the list so that there will be no duplicate items

		// add each item to the marketplace
		for (Gadget gadget : gadgets) {
			marketplace.add(gadget);
		}

	}

	/* Opens another form depending on the input of the user */
	private static void launchShop() throws InterruptedException {

		int num;
		do {
			num = loginOrRegister(); // this will return the user input
			if (num == 1) {
				scanner.nextLine(); // para sa nextInt() sa loginOrRegister();
				loginForm(); // the login form will open
			} else if (num == 2) {
				scanner.nextLine(); // para sa nextInt() sa loginOrRegister();
				registerForm(); // the register form will open
			} else if (num == 3) {
				System.out.println("\n***** Thank you. Please come again! *****");
				System.exit(0); // the program will terminate
			} else {
				System.out.println("\n**LOG: Invalid input. Please try again.");
			}
		} while (num != 1 && num != 2);

	}

	/* Asks the user whether to login, register, or exit */
	private static int loginOrRegister() {
		int num = 0;
		System.out.println("\n============= [ LOGIN or REGISTER ] =============");
		System.out.println("[1] Login");
		System.out.println("[2] Register");
		System.out.println("[3] Exit");
		System.out.print("Choose a number: ");
		try {
			num = scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.next(); // handles the bug/error
		}

		return num;

	}

	/**
	 * 
	 * This method asks for the username and password from the customer checks if
	 * the username and password are correct redirects to Menu if correct opens the
	 * login form again if incorrect
	 * 
	 */
	private static void loginForm() throws InterruptedException {
		setUsers(); // updates the collection of login credentials
		setCustomers(); // updates the collection of customers
		System.out.println("\n=================== [ LOGIN ] ===================");
		System.out.println("Note: Type \"BACK\" in the username to exit");
		System.out.print("\nUsername: ");
		username = scanner.nextLine();

		if (username.equals("BACK")) {
			launchShop();
			return; // exits the method
		}

		System.out.print("Password: ");
		password = scanner.nextLine();

		if (users.containsKey(username)) { // checks if the collection contains the input username
			if (users.get(username).equals(password)) { // checks if the password is correct
				System.out.println("\n**LOG: Successfully logged in. Opening Menu...");
				showMenu();

			} else {
				System.out.println("\n**LOG: Wrong password. Please try again.");
				loginForm();
			}
		} else {
			System.out.println("\n**LOG: This username does not exist. Please try again.");
			loginForm();
		}

	}

	/**
	 * 
	 * This method asks the desired username and password from the customer informs
	 * the user if the username is already taken this will also ask for the
	 * information of the customer once done, the inputs will be saved to their
	 * respective txt file and the Menu will open
	 * 
	 */
	private static void registerForm() throws InterruptedException {
		setUsers();
		setCustomers();
		System.out.println("\n================= [ REGISTER ] ==================");
		System.out.println("Note: Type \"BACK\" in the username to exit");
		System.out.print("\nUsername: ");
		username = scanner.nextLine();

		if (username.equals("BACK")) {
			launchShop();
			return; // exits the method
		}

		System.out.print("Password: ");
		password = scanner.nextLine();

		if (users.containsKey(username)) {
			System.out.println("\n**LOG: This username is already taken. Please try again.");
			registerForm();
		} else {
			System.out.println("\n================ [ CUSTOMER INFORMATION ] ================");
			System.out.print("Name: ");
			name = scanner.nextLine();
			System.out.print("Address: ");
			address = scanner.nextLine();
			System.out.print("Number: ");
			number = scanner.nextLine();

			customers.add(new Customer(name, username, address, number));
			users.put(username, password);

			// updating the txt file
			LoginCredentials.write(users);
			CustomerInformation.write(customers);

			System.out.println("\n**LOG: Successfully registered. Opening Menu...");

			showMenu();
		}

	}

	/* Shows various option to the customer */
	private static void showMenu() throws InterruptedException {
		Thread.sleep(1000);
		getCustomerInfo(); // finds the customer information from the collection by using the username
		int num = 0;

		System.out.println("\n=================== [ MENU ] ===================");
		System.out.println("e-Coins: " + customer.getCoins());
		System.out.println("\n[1] View products");
		System.out.println("[2] Add to cart");
		System.out.println("[3] View cart");
		System.out.println("[4] Checkout");
		System.out.println("[5] Cash-in");
		System.out.println("[6] Logout");
		System.out.println("[7] Exit");
		System.out.print("Choose a number: ");
		try {
			num = scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.next();
		}

		switch (num) {

		// View Products
		case 1:
			scanner.nextLine();
			marketplace.viewProducts();
			System.out.print("\nPress Enter to go back to Menu");
			scanner.nextLine();
			showMenu();
			break;

		// Add to Cart
		case 2:
			System.out.println("\n================== [ SELECT PRODUCT ] ==================");
			int select = 0, index = 0;

			// prints all the name of the items available
			for (Product item : marketplace.getItems()) {
				index++;
				System.out.println("[" + index + "] " + item.getName());
			}

			System.out.print("Select an item to add to cart: ");
			try {
				select = scanner.nextInt();
				if (select > marketplace.getItems().size() || select == 0)
					throw new ArithmeticException(); // throws an error if the input is out of range

				for (Product itemCart : customer.getCart()) {
					// throws an error if the desired item is already added to cart
					if (marketplace.getItems().get(select - 1).getName().equals(itemCart.getName()))
						throw new IOException();
				}

				customer.addToCart(marketplace.getItems().get(select - 1));
				System.out.println("\n**LOG: Successfully added to cart. Returning to Menu...");
			} catch (InputMismatchException e) {
				scanner.next();
				System.out.println("\n**LOG: Invalid input. Returning to Menu...");
			} catch (ArithmeticException e) {
				System.out.println("\n**LOG: Invalid input. Returning to Menu...");
			} catch (IOException e) {
				System.out.println("\n**LOG: Item is already in cart. Returning to Menu...");
			}
			showMenu();
			break;

		// View Cart
		case 3:
			scanner.nextLine();
			customer.viewCart();
			System.out.print("\nPress Enter to go back to Menu");
			scanner.nextLine();
			showMenu();
			break;

		// Checkout
		case 4:
			scanner.nextLine();
			customer.checkOut(marketplace);
			CustomerInformation.write(customers);
			if (marketplace.getItems().size() <= 5)
				addProductsInMarketPlace();
			System.out.print("\nPress Enter to go back to Menu");
			scanner.nextLine();
			showMenu();
			break;

		// Cash-in
		case 5:
			float addCoins;
			System.out.println("\n======================== [ CASH-IN ] ========================");
			System.out.print("Enter e-Coins to cash-in: ");
			try {
				addCoins = scanner.nextFloat();
				customer.setCoins(customer.getCoins() + addCoins);
				CustomerInformation.write(customers);
				System.out.println("\n**LOG: Successfully cashed in " + addCoins + " coins. Returning to Menu...");
			} catch (InputMismatchException e) {
				System.out.println("\n**LOG: Invalid input. Returning to Menu...");
			}
			showMenu();
			break;

		// Logout
		case 6:
			customer = null;
			username = "";
			password = "";
			launchShop();
			break;

		// Exit
		case 7:
			System.out.println("\n***** Thank you. Please come again! *****");
			System.exit(0); // terminates the program
			break;

		// Invalid input
		default:
			System.out.println("\n**LOG: Invalid input. Please try again!");
			showMenu();
		}

	}

	/* Gets the information of the active customer */
	private static void getCustomerInfo() {
		for (Customer customer1 : customers) {
			if (customer1.getUsername().equals(username)) {
				customer = customer1;
				break;
			}
		}

	}

}
