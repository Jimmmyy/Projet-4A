package esiea.model;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;





public class SupermarketTest {

   
   
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
   	public void EqualsReceiptTest() {
   		Product apples = new Product("apples", ProductUnit.Kilo);
   		Product toothbrush = new Product("toothbrush", ProductUnit.Kilo);

   		ReceiptItem receiptItem = new ReceiptItem(apples, 2.0, 1.99, 3.98);
           ReceiptItem receiptItem1 = new ReceiptItem(toothbrush, 3.0, 0.99, 0.97);
           ReceiptItem receiptItem2 = new ReceiptItem(apples, 1.0, 1.99, 3.98);
           ReceiptItem receiptItem3 = new ReceiptItem(apples, 2.0, 2.99, 3.98);
           ReceiptItem receiptItem4 = new ReceiptItem(apples, 2.0, 1.99, 4.98);
           ReceiptItem receiptItem5 = new ReceiptItem(apples, 2.0, 1.99, 3.98);
           ReceiptItem receiptItem6 = new ReceiptItem(toothbrush, 2.0, 1.99, 3.98);

           Assertions.assertThat(receiptItem.equals(receiptItem)).isEqualTo(true);
           Assertions.assertThat(receiptItem).isNotEqualTo(null);
           Assertions.assertThat(receiptItem.equals(receiptItem1)).isEqualTo(false);
           Assertions.assertThat(receiptItem.equals(receiptItem2)).isEqualTo(false);
           Assertions.assertThat(receiptItem.equals(receiptItem3)).isEqualTo(false);
           Assertions.assertThat(receiptItem.equals(receiptItem4)).isEqualTo(false);
           Assertions.assertThat(receiptItem.equals(receiptItem5)).isEqualTo(true);
           Assertions.assertThat(receiptItem.equals(receiptItem6)).isEqualTo(false);

           Assertions.assertThat(receiptItem.equals(apples)).isEqualTo(false);

       }
       @Test
       public void hashCodeTest(){
           Product product = new Product("product",ProductUnit.Kilo);
           ReceiptItem receiptItem = new ReceiptItem(product, 2.0, 1.99, 3.98);
           Assertions.assertThat(receiptItem.hashCode()).isEqualTo(Objects.hash(product, 1.99, 3.98, 2.0));
       }
    
}
