package esiea.model;

import java.util.ArrayList;
import java.util.List;

public class Discount {
    private final String description;
    private final double discountAmount;
    private final List<Product> products;

    public Discount(List<Product> products, String description, double discountAmount) {
        this.products = products;
        this.description = description;
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return products.get(0);
    }
    
    public Product getProduct(int index) {
        return products.get(index);
    }
    
    public List<Product> getAllProducts() {
    	List<Product> result = new ArrayList<Product>();
    	for(int index = 0;index<products.size();index++) {
    		result.add(products.get(index));
    	}
    	return result;
    }

}
