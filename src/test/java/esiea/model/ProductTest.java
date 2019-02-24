package esiea.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

	
	  @Test
	    public void testEqualsProduct () {

	        Product apples = new Product("apples", ProductUnit.Kilo);

	        Discount discount = new Discount(apples, "Apples Discount", 0.199);

	        Product banane = new Product("banane", ProductUnit.Kilo);
	        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	        Product apples1 = new Product("apples", ProductUnit.Kilo);

	        Assertions.assertThat(apples.equals(apples)).isEqualTo(true);
	        Assertions.assertThat(apples.equals(null)).isEqualTo(false);

	        Assertions.assertThat(apples.equals(discount)).isEqualTo(false);
	        Assertions.assertThat(apples.equals(banane)).isEqualTo(false);
	        Assertions.assertThat(apples.equals(toothbrush)).isEqualTo(false);
	        Assertions.assertThat(apples.equals(apples1)).isEqualTo(true);


	    }
}
