package esiea.model;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import java.util.*;

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
	        List<Product> product_offer = Arrays.asList(toothbrush);
	        
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product_offer, 10.0);

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
	        List<Product> product_offer = Arrays.asList(apples);
	        
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, product_offer, 10.0);

	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        
	        double expected = 3*1.99-3*1.99*0.1;
	        
	        assertThat(receipt.getTotalPrice()).isEqualTo(expected);
	        
	     } 
	    
	  /* @Test
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
	        
	    }*/
	   
	   @Test
	   
	   public void test3pour2offre() {
		   
		   SupermarketCatalog catalog = new FakeCatalog();
           Teller teller = new Teller(catalog);
           Product tampax = new Product("tampax", ProductUnit.Each);
           List<Product> product_offer = Arrays.asList(tampax);
           catalog.addProduct(tampax, 3.49);
           teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, product_offer, 0);
           
 
           ShoppingCart cart = new ShoppingCart();
           cart.addItemQuantity(tampax, 2);   
           
           double SuposedCartPrice = 2 * 3.49;
           double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
           
           assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2 boite de tampax pour le prix de 2"); 
           
           cart.addItem(tampax); 
           SuposedCartPrice = 2 * 3.49;
           RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
           assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2 boite de tampax pour le prix de 3"); 
	   }
	   
	    
	   @Test
       public void testtwoForAmount()
       {
        
        SupermarketCatalog catalog = new FakeCatalog();
        Teller teller = new Teller(catalog);
        
        Product rasoir = new Product("rasoir", ProductUnit.Each);
        catalog.addProduct(rasoir, 2.5);
        List<Product> product_offer = Arrays.asList(rasoir);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, product_offer, 4);

        
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(rasoir, 1);
        double SuposedCartPrice = (2.5);
        double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("Pas de reduc sur l'article"); 

        
        cart.addItemQuantity(rasoir, 1);
        SuposedCartPrice = (4);
        RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2 articles avec reduc"); 


    }

	    @Test
	    public void testOffreFiveForAmount() {
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product kiwi = new Product("kiwi", ProductUnit.Each);
	        catalog.addProduct(kiwi, 2.00);
	        List<Product> product_offer = Arrays.asList(kiwi);
	        
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, product_offer, 7.50);
	        
	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(kiwi, 3);
	        
	        double SuposedCartPrice =  2*3;
	        double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
	        
	        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("3 articles prix de base");
	        cart.addItemQuantity(kiwi, 2);
	        SuposedCartPrice =  2*5 - ((2*5)*0.25) ;
	        RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
	        
	        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("5 article a - 25%"); 
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
	    
	    
	    @Test
	    public void testOffreBundle() {
	    	
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	        catalog.addProduct(toothbrush, 1.00);
	        Product apples = new Product("apples", ProductUnit.Kilo);
	        catalog.addProduct(apples, 0.50);

	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(apples, 1);
	        cart.addItemQuantity(toothbrush, 1);
	        List<Product> product_offer = Arrays.asList(toothbrush,apples);
	        
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.Bundle, product_offer, 10.0);
	        System.out.println();
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        double expected_price = 1 + 0.5 - 1.5*0.1;
	        assertThat(receipt.getTotalPrice()).isNotEqualTo(expected_price);
	    	
	    }
	    
	    @Test
	    public void testOffreBundleSimple() {
	    	
	    	SupermarketCatalog catalog = new FakeCatalog();
	        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	        catalog.addProduct(toothbrush, 1.00);
	        Product apples = new Product("apples", ProductUnit.Kilo);
	        catalog.addProduct(apples, 0.50);

	        ShoppingCart cart = new ShoppingCart();
	        cart.addItemQuantity(apples, 1);
	        List<Product> product_offer = Arrays.asList(apples);
	        
	        Teller teller = new Teller(catalog);
	        teller.addSpecialOffer(SpecialOfferType.Bundle, product_offer, 10.0);
	        System.out.println();
	        Receipt receipt = teller.checksOutArticlesFrom(cart);
	        double expected_price = 0.5 - 0.5*0.1;
	        assertThat(receipt.getTotalPrice()).isEqualTo(expected_price);
	    	
	    }
}