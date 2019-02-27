package esiea.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;

public class OfferTest {

	@Test
	public void TestMethodGetProduct() {
		List<Product> product_offer = Arrays.asList(new Product("poire", ProductUnit.Kilo));
		Offer myOffer = new Offer(SpecialOfferType.TenPercentDiscount, product_offer, 3.99);
		Assertions.assertThat(myOffer.getProduct().getName()).isEqualTo("poire");

		Assertions.assertThat(myOffer.getProduct().getName()).isNotEmpty();

		Assertions.assertThat(myOffer.getProduct().getName()).isNotEqualTo("toothbrush");

	}

	@Test
	public void TestMethodGetAllProducts() {
		//TODO : methode getAllProducts() dans la classe Offer
		
		List<Product> product_offer = new ArrayList<Product>();
		List<Product> product_in_cart = new ArrayList<Product>();
		Product poire = new Product("poire", ProductUnit.Kilo);
		Product pomme = new Product("pomme", ProductUnit.Kilo);
		product_offer.add(poire);
		product_offer.add(pomme);
		product_in_cart.add(poire);
		product_in_cart.add(pomme);
		Offer myOffer = new Offer(SpecialOfferType.Bundle, product_offer, 10.0);
		
		Assertions.assertThat(myOffer.getAllProducts().containsAll(product_offer));
	}
	
	
	
}
