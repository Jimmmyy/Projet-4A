package esiea.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    SpecialOfferType offerType;
    private final List<Product> products; //on assimile une offre a plusieurs produits
    double argument;

    public Offer(SpecialOfferType offerType, List<Product> products, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.products = products;
    }
    
    //retourne le premier element de la liste de produit
    public Product getProduct() {
    	return products.get(0);
    }
    
    //retourne l'element à la position index de la liste de produit
    public Product getProduct(int index) {
    	return products.get(index);
    }
    
    //retourne la taille de la liste des produits
    public int getListProductsSize() {
    	return products.size();
    }
    
    //retourne une nouvelle liste contenant tous les produits présents dans la liste
    public List<Product> getAllProducts() {
    	List<Product> result = new ArrayList<Product>();
    	for(int index = 0;index<products.size();index++) {
    		result.add(products.get(index));
    	}
    	return result;
    }

}