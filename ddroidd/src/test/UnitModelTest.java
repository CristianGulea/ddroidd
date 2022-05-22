package test;

import model.Product;
import model.ShippingRate;
import model.Order;
import model.Tuple;
import org.junit.Test;

import java.util.HashMap;

import static org.testng.AssertJUnit.assertEquals;

public class UnitModelTest {

    @Test
    public void testProduct(){
        Product product = new Product("test", 1.2, "UK", 1.9);
        //Test getter
        assertEquals(product.getName(), "test");
        assertEquals(product.getPrice(), 1.2);
        assertEquals(product.getShippedFrom(), "UK");
        assertEquals(product.getWeight(), 1.9);
        product.setName("setter");
        product.setPrice(3.3);
        product.setShippedFrom("RO");
        product.setWeight(3.3);
        //Test setter
        assertEquals(product.getName(), "setter");
        assertEquals(product.getPrice(), 3.3);
        assertEquals(product.getShippedFrom(), "RO");
        assertEquals(product.getWeight(), 3.3);
    }

    @Test
    public void testShippingRate(){
        ShippingRate shippingRate = new ShippingRate("RO", 1);
        //Test getter
        assertEquals(shippingRate.getRate(), 1.0);
        assertEquals(shippingRate.getCountry(), "RO");
        shippingRate.setRate(2.0);
        shippingRate.setCountry("US");
        //Test setter
        assertEquals(shippingRate.getRate(), 2.0);
        assertEquals(shippingRate.getCountry(), "US");
    }

    @Test
    public void testOrder(){
        Order order = new Order();
        //Test getter
        assertEquals(order.getShipping(), 0.0);
        assertEquals(order.getSubtotal(), 0.0);
        assertEquals(order.getVAT(), 0.0);
        assertEquals(order.getCart().size(), 0);
        order.setShipping(1.1);
        order.setVAT(1.1);
        order.setSubtotal(1.1);
        order.setCart(new HashMap<>(){{
            put("Mouse", new Tuple<>(new Product("Mouse", 10.99, "RO", 0.2), 2));
            put("Keyboard", new Tuple<>(new Product("Keyboard", 40.99, "UK", 0.7), 1));
        }});
        //Test setter
        assertEquals(order.getShipping(), 1.1);
        assertEquals(order.getSubtotal(), 1.1);
        assertEquals(order.getVAT(), 1.1);
        assertEquals(order.getCart().get("Mouse").getLeft(), new Product("Mouse", 10.99, "RO", 0.2));
        assertEquals(order.getCart().get("Keyboard").getLeft(), new Product("Keyboard", 40.99, "UK", 0.7));
        int numberOfProducts = order.getCart().get("Mouse").getRight();
        assertEquals(numberOfProducts, 2);
        numberOfProducts = order.getCart().get("Keyboard").getRight();
        assertEquals(numberOfProducts, 1);
    }

    @Test
    public void testTuple(){
        Tuple<Integer, String> tuple = new Tuple<>(1, "1");
        //Test getter
        int value = tuple.getLeft();
        assertEquals(value, 1);
        String valueString = tuple.getRight();
        assertEquals(valueString, "1");
        tuple.setRight("2");
        tuple.setLeft(2);
        //Test setter
        value = tuple.getLeft();
        assertEquals(value, 2);
        valueString = tuple.getRight();
        assertEquals(valueString, "2");
    }
}
