package esiea.model;

import org.junit.jupiter.api.Test;
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
	
}
