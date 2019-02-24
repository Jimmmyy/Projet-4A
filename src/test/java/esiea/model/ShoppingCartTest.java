package esiea.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;


public class ShoppingCartTest {
	
	private ShoppingCart cart = new ShoppingCart();
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	private Product poire = new Product ("poire", ProductUnit.Kilo);

	 @Test
	    public void testPrixPommeAvecReducBrosseADents() {
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

	        //test : combien coute 2.5 kg de pommes à 1.99€ compte tenu du fait qu'une
	        // réduction est en court sur les brosses à dents.
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(2.5*1.99);
	    }
	    
	    @Test
	    public void testPrixPommeAvecReducPomme() {
	        SupermarketCatalog catalog = new FakeCatalog();
	        Product apples = new Product("apples", ProductUnit.Kilo);
	        catalog.addProduct(apples, 1.99);

	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(apples, 3);

	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, apples, 10.0);

	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        
	        double expected = 3*1.99-3*1.99*0.1;
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(expected);
	        
	     }
	    
	    @Test
	    public void testOffreThreeForTwo() {
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	        catalog.addProduct(toothbrush, 1.0);
	        
	        Product poire = new Product("poire", ProductUnit.Each);
	        catalog.addProduct(poire, 1.0);
	        cart.addItemQuantity(poire, 6);
	        
	        Map<Product, Offer> listeDesPromos=new HashMap<>();
	        
	        //Ajout de la promotion dans le catalogue de promotions
	        Offer offrethreefortwo=new Offer(SpecialOfferType.ThreeForTwo,poire,4);
	        listeDesPromos.put(poire,offrethreefortwo);
	                
	        
	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(toothbrush, 6.0);
	        
	     
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 4.0);
	        
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	     
	        cart.handleOffers(receipt,listeDesPromos,catalog);
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(4.0);
	    }
	    
	    @Test
	    public void testOffreTwoForAmount() {
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product apple = new Product("apple", ProductUnit.Kilo);
	        catalog.addProduct(apple, 1.10);
	        
	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(apple, 2);
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, apple, 1.90);
	        
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(1.90);
	    }
	    
	    @Test
	    public void testOffreFiveForAmount() {
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product kiwi = new Product("kiwi", ProductUnit.Kilo);
	        catalog.addProduct(kiwi, 1.50);
	        
	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(kiwi, 5);
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, kiwi, 4.80);
	        
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(4.80);
	    }
	    
	    @Test
	    public void testPrixSansOffre() {
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product porsche = new Product("porsche", ProductUnit.Each);
	        catalog.addProduct(porsche, 100000);
	        
	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(porsche, 1);
	        Teller teller = new Teller(catalog);
	        
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        assertThat(receipt.getTotalPrice()).isEqualTo(100000);
	    }
	    
	    @Test
		public void testProductQuantitiesNotEmptyIfAddItem() {
			cart.addItem(toothbrush);
			Map<Product, Double> productQuantities = cart.productQuantities();
			
			Assertions.assertNotNull(productQuantities);
		}
	    
	    @Test
	    public void testAjoutProduitDejaExistantDansLaListe() {
	    	
	    	cart.addItem(poire);
	    	cart.addItemQuantity(poire, 3);
	    	Map<Product, Double> productQuantities = cart.productQuantities();
	    	double quantite=productQuantities.get(poire);
	    	Assertions.assertEquals(4,quantite);

	    }
}
