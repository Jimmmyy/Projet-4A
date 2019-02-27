package esiea.model;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountTest {

	@Test
	public void getAllProductsTest() {
		Product pommes = new Product("pommes", ProductUnit.Kilo);
		Product poires = new Product("poires", ProductUnit.Kilo);
		List<Product> list_products = new ArrayList<Product>();
		list_products.add(pommes);
		list_products.add(poires);
		Discount discount = new Discount(list_products,"test discount", 10.0);
		
		Assertions.assertThat(discount.getAllProducts().containsAll(list_products));
	}
	
	public void getProductTest() {
		Product pommes = new Product("pommes", ProductUnit.Kilo);
		Product poires = new Product("poires", ProductUnit.Kilo);
		List<Product> list_products = new ArrayList<Product>();
		list_products.add(pommes);
		list_products.add(poires);
		Discount discount = new Discount(list_products,"test discount", 10.0);
		Assertions.assertThat(discount.getProduct(1)).isEqualTo(poires);
		Assertions.assertThat(discount.getProduct(1)).isNotEqualTo(pommes);
		Assertions.assertThat(discount.getProduct(1)).isNotNull();
	}

}
