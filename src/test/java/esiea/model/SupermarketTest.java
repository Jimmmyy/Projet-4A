package esiea.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // TODO: complete this test
        
        //test : combien coute 2.5 kg de pommes à 1.99€ compte tenu du fait qu'une
        // réduction est en court sur les brosses à dents.
        
        assertThat(receipt.getTotalPrice()).isEqualTo(2.5*1.99);
        // WOW CA MARCHE
        
        
        //List<Product> products = Arrays.asList(new Product("toto", ProductUnit.Each));
        //assertThat(true).isTrue();
        //assertThat(products).extracting(Product::getName).as("Product names").containsExactly("apple", "orange");
    }
}
