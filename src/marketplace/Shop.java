package marketplace;

import java.util.ArrayList;

/**
 *
 * An abstract class and the superclass of Marketplace Class
 * 
 */

public abstract class Shop {
    protected ArrayList<Product> items = new ArrayList<Product>();
    protected String name;
    protected int remainingStocks;

    public Shop(String name) {
        this.name = name;
    }

    /* This accepts a Product and adds it on the product list. */
    protected abstract void add(Product item);

    /* This removes a product from list. */
    protected abstract void remove(Product item);

    /* This prints a listing of items of the shop. */
    protected abstract void viewProducts();

    /* This is a getter for shop name */
    protected abstract String getName();

    /* This is a getter for items */
    protected abstract ArrayList<Product> getItems();
    
}
