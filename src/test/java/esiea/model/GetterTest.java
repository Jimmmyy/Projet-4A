package esiea.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetterTest {
	
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
	    public void testGetUnitProduct(){
	    	Product pomme= new Product("pomme",ProductUnit.Kilo);
	    	ProductUnit Unite=pomme.getUnit();
	    	assertEquals(Unite,ProductUnit.Kilo);
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
	    public void getDescriptiontest(){
	        Product apples = new Product("apples", ProductUnit.Kilo);
	        Discount testDiscount = new Discount(apples, "Apples Discount", 1.99);

	        Assertions.assertThat(testDiscount.getDescription()).isEqualTo("Apples Discount");
	    }
	 
	    
	    @Test
	    public void getProducttest(){
	        Product apples = new Product("apples",ProductUnit.Kilo);
	        Discount testDiscount = new Discount(apples, "Apples Discount", 1.99);

	        Assertions.assertThat(testDiscount.getProduct()).isEqualTo(apples);
	    }
	    
	    
	
	

}
