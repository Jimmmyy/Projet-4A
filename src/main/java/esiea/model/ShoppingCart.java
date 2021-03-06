package esiea.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();
    

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
        	
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
            	List<Product> current_product_with_offer = new ArrayList<Product>();
            	List<Product> product_with_bundle_offer = new ArrayList<Product>();
            	List<Product> liste_bundle = new ArrayList<Product>();
            	boolean allBundleProductsAreInCart = false;
            	double bundle_discount_total = 0.0;
            	current_product_with_offer.add(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;
                Discount discount = null;
                int x = 1;
                if (offer.offerType == SpecialOfferType.ThreeForTwo) {
                    x = 3;

                } else if (offer.offerType == SpecialOfferType.TwoForAmount) {
                    x = 2;
                    if (quantityAsInt >= 2) {
                        double total = offer.argument * quantityAsInt / x + quantityAsInt % 2 * unitPrice;
                        double discountN = unitPrice * quantity - total;
                        discount = new Discount(current_product_with_offer, "2 for " + offer.argument, discountN);
                    }

                } if (offer.offerType == SpecialOfferType.FiveForAmount) {
                    x = 5;
                }
                int numberOfXs = quantityAsInt / x;
                if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
                    discount = new Discount(current_product_with_offer, "3 for 2", discountAmount);
                }
                if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
                    discount = new Discount(current_product_with_offer, offer.argument + "% off", quantity * unitPrice * offer.argument / 100.0);
                }
                if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
                    discount = new Discount(current_product_with_offer, x + " for " + offer.argument, discountTotal);
                }
                if (offer.offerType == SpecialOfferType.Bundle) {
                	
                	product_with_bundle_offer.add(p);
                	if (!liste_bundle.isEmpty()) {
                		liste_bundle = offer.getAllProducts();
                	}
                	if(product_with_bundle_offer.containsAll(liste_bundle) && allBundleProductsAreInCart) {
                		//discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
                		
                	}
                	discount = new Discount(current_product_with_offer, offer.argument + "% off", quantity * unitPrice * offer.argument / 100.0);
            		
                	//discount = new Discount(product_offer, offer.argument + "% off bundle", quantity * unitPrice * offer.argument / 100.0);
                	//offer.getAllProducts
                	//
                	
                		//if (liste_bundle.contains(p)) {
                			//for(int index = 0; index<offer.getListProductsSize(); index++) {
                				//allBundleProductsAreInCart = true;
                			//}
                		//}
                	//if (allBundleProductsAreInCart) {
            			//discount = new Discount(product_offer, offer.argument + "% off", quantity * unitPrice * offer.argument / 100.0);
            		//}

                    //regarder si chaque element dans la liste de bundle 
                    //est présent dans la liste des elements du charriot 
                    //for each product p in Offer on regarde si liste.contains(p)
                } 
                if (discount != null)
                    receipt.addDiscount(discount);
            }

        }
    }
}