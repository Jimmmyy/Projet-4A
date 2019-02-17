package esiea.model;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;




public class SupermarketTest {

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
        catalog.addProduct(toothbrush, 0.99);
        
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 1);
        
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        
        assertThat(receipt.getTotalPrice()).isEqualTo(0.99*2);
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
    public void testListePanier() {
    	List<Product> products = Arrays.asList(new Product("zoulou", ProductUnit.Each), new Product("tango", ProductUnit.Each));
        assertThat(products).extracting(Product::getName).as("Product names").containsExactly("zoulou", "tango"); 	
    }
    
    @Test
    public void testProduct() {
    	Product pomme= new Product("pomme",ProductUnit.Kilo);
    	boolean truecondition=pomme.equals(pomme);
    	assertTrue(truecondition);
    	
    	boolean falsecondition=pomme.equals(null);
    	assertFalse(falsecondition);
    	
    	
    }
    
   @Test
    public void testWrongProduct(){
    	Product pomme= new Product("pomme",ProductUnit.Kilo);
    	Object obj = new Object();
    	assertNotEquals(pomme,obj);
    }
   
    @Test
    public void testNullProduct(){
    	Product pomme= new Product("pomme",ProductUnit.Kilo);
    	boolean condition=pomme.equals(null);
    	assertFalse(condition);
    }
    
    @Test
    public void testGetUnitProduct(){
    	Product pomme= new Product("pomme",ProductUnit.Kilo);
    	ProductUnit Unite=pomme.getUnit();
    	assertEquals(Unite,ProductUnit.Kilo);
    }
    
    @Test
    public void testGetterItemsReceipt(){
    	Receipt ticket= new Receipt();
    	List<ReceiptItem> items= new ArrayList<>();
    	List<ReceiptItem> itemsdelobjet=ticket.getItems();
    	
    	assertEquals(items,itemsdelobjet);
    }
    
    @Test
    public void testGetterDiscountsReceipt(){
    	
    	Receipt ticket= new Receipt();
    	List<Discount> discounts = new ArrayList<>();
    	List<Discount> discountsdelobjet = ticket.getDiscounts();
    	
    	assertEquals(discounts,discountsdelobjet);
    }
    
    @Test
    public void testGetterPrice(){
    	
    	Product product=new Product("pomme",ProductUnit.Kilo);
    	ReceiptItem ticket= new ReceiptItem(product,2.0,10.0,20.0);
    	double prix=ticket.getPrice();
    	double prix2=10.0;
    	assertEquals(prix,prix2);
    }
    
    @Test
    
    public void testGetterProduct(){
    	Product product=new Product("pomme",ProductUnit.Kilo);
    	ReceiptItem ticket= new ReceiptItem(product,2.0,10.0,20.0);
    	Product verifProduct = ticket.getProduct();
    	assertEquals(product,verifProduct);
    }
    
    
    @Test
    
    public void testGetterQuantity(){
    	Product product=new Product("pomme",ProductUnit.Kilo);
    	ReceiptItem ticket1= new ReceiptItem(product,2.0,10.0,20.0);
    	double verifquantityproduct = ticket1.getQuantity();
    	assertEquals(2.0, verifquantityproduct);
    	
    }
    
    @Test
   
    public void testNoDiscount () {

       SupermarketCatalog catalog = new FakeCatalog();
       Product cherry = new Product("cherry", ProductUnit.Kilo);
       catalog.addProduct(cherry, 1.99);

       ShoppingCart cart = new ShoppingCart();
       cart.addItemQuantity(cherry, 2);

       Teller teller = new Teller(catalog);
     
       Receipt receipt = teller.checksOutArticlesFrom(cart);

       Assertions.assertThat(receipt.getTotalPrice()).isEqualTo(3.98);

    }
    
    @Test
    public void getDescriptiontest(){
        Product apples = new Product("apples", ProductUnit.Kilo);
        Discount testDiscount = new Discount(apples, "Apples Discount", 1.99);

        Assertions.assertThat(testDiscount.getDescription()).isEqualTo("Apples Discount");
    }
 
}
