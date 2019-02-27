package esiea.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer {
    SpecialOfferType offerType;
    private final List<Product> products;
    double argument;

    public Offer(SpecialOfferType offerType, List<Product> products, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.products = products;
    }
    
    public Product getProduct() {
    	return products.get(0);
    }
    
    public List<Product> getAllProducts() {
    	List<Product> result = new ArrayList<Product>();
    	for(int index = 0;index<products.size();index++) {
    		result.add(products.get(index));
    	}
    	return result;
    }

}
