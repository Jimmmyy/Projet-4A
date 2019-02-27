package esiea.model;

import org.junit.jupiter.api.Test;

import java.awt.List;

import org.assertj.core.api.Assertions;

public class OfferTest {

	@Test
	public void TestMethodGetProduct() {
		Product poire = new Product("poire", ProductUnit.Kilo);
		Offer Myoffer = new Offer(SpecialOfferType.TenPercentDiscount, poire, 3.99);
        
		Assertions.assertThat(Myoffer.getProduct().getName()).isEqualTo("poire");

		Assertions.assertThat(Myoffer.getProduct().getName()).isNotEmpty();

		Assertions.assertThat(Myoffer.getProduct().getName()).isNotEqualTo("toothbrush");

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
		
		Assertions.assertThat(product_offer.contains(poire));
		
		Assertions.assertThat(myOffer.getAllProducts().containsAll(product_offer));
	}
	
}
