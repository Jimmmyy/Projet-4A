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
		Offer Myoffer = new Offer(SpecialOfferType.TenPercentDiscount, product_offer, 3.99);
		Assertions.assertThat(Myoffer.getProduct().getName()).isEqualTo("poire");

		Assertions.assertThat(Myoffer.getProduct().getName()).isNotEmpty();

		Assertions.assertThat(Myoffer.getProduct().getName()).isNotEqualTo("toothbrush");

	}

	
}
