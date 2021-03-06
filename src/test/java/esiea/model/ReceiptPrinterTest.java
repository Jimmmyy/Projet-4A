package esiea.model;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import esiea.ReceiptPrinter;

public class ReceiptPrinterTest {
	
	
		
		 @Test
		    public void testPrintReceipt(){
		        SupermarketCatalog catalog = new FakeCatalog();
		        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		        catalog.addProduct(toothbrush, 0.99);
		        Product apple = new Product("apple", ProductUnit.Kilo);
		        catalog.addProduct(apple, 2.10);

		        ShoppingCart cart = new ShoppingCart();

		        cart.addItemQuantity(toothbrush, 4);
		        cart.addItemQuantity(apple, 1.00);
		        List<Product> product_offer = Arrays.asList(toothbrush);
		        Teller teller = new Teller(catalog);
		        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, product_offer, 0.99);
		        Receipt receipt = teller.checksOutArticlesFrom(cart);

		        ReceiptPrinter receiptPrinter = new ReceiptPrinter();

		        String stringToTest = receiptPrinter.printReceipt(receipt);
		        String expectedString = "toothbrush"+"                          "+"3.96"+"\n"+"  "+"0.99"+" * "+"4"+"\n"+"apple"+"                               "+"2.10"+"\n"+"2 for 0.99"+"("+"toothbrush"+")"+"             "+"-"+"1.98"+"\n"+"\n"+"Total: "+"                             "+"4.08";


		        Assertions.assertEquals(expectedString, stringToTest);
		    }
		 
		 
		 
		 
		 
	 

}
