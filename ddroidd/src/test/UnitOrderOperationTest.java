package test;
import model.Order;
import model.Product;
import model.ShoppingData;
import org.junit.Before;
import org.junit.Test;
import utils.OrderOperation;
import utils.ProductException;

import static org.junit.Assert.assertThrows;
import static org.testng.AssertJUnit.*;


public class UnitOrderOperationTest {
    private ShoppingData shoppingData;
    private OrderOperation orderOperation;

    @Before
    public void setUp() throws Exception {
        shoppingData = new ShoppingData();
        orderOperation = new OrderOperation(shoppingData);
    }

    @Test
    public void testAddProductToCart() throws ProductException {
        Order order = new Order();
        assertEquals(order.getCart().size(), 0);
        //Add a new product
        orderOperation.addProductToCart(order, "Mouse");
        int numberOfProducts = order.getCart().get("Mouse").getRight();
        assertEquals(order.getSubtotal(), 10.99);
        assertEquals(order.getShipping(), 2.0);
        assertEquals(order.getVAT(), 2.0881);
        assertEquals(order.getCart().size(), 1);
        assertEquals(order.getCart().get("Mouse").getLeft(), new Product("Mouse", 10.99, "RO", 0.2));
        assertEquals(numberOfProducts, 1);
        //Add an existing product
        orderOperation.addProductToCart(order, "Mouse");
        numberOfProducts = order.getCart().get("Mouse").getRight();
        assertEquals(order.getSubtotal(), 21.98);
        assertEquals(order.getShipping(), 4.0);
        assertEquals(order.getVAT(), 4.1762);
        assertEquals(order.getCart().size(), 1);
        assertEquals(order.getCart().get("Mouse").getLeft(), new Product("Mouse", 10.99, "RO", 0.2));
        assertEquals(numberOfProducts, 2);
        //Add a new product
        orderOperation.addProductToCart(order, "Webcam");
        assertEquals(order.getSubtotal(), 106.97);
        assertEquals(order.getVAT(), 20.3243);
        assertEquals(order.getShipping(), 6.0);
        assertEquals(order.getCart().get("Webcam").getLeft(), new Product("Webcam", 84.99, "RO", 0.2));
        //Try to add invalid products
        assertThrows(ProductException.class, () -> {
            orderOperation.addProductToCart(order, "Car");
        });
        assertThrows(ProductException.class, () -> {
            orderOperation.addProductToCart(order, "Desk");
        });
        assertEquals(order.getCart().size(), 2);
    }

@Test
public void testDiscount() throws ProductException {
        Order order = new Order();
        //Calculate discounts
        assertEquals(orderOperation.calculateDeskLampDiscount(order), 0.0);
        assertEquals(orderOperation.calculateKeyboardDiscount(order), 0.0);
        assertEquals(orderOperation.calculateShippingDiscount(order), 0.0);
        orderOperation.addProductToCart(order, "Mouse");
        assertEquals(orderOperation.calculateDeskLampDiscount(order), 0.0);
        assertEquals(orderOperation.calculateKeyboardDiscount(order), 0.0);
        assertEquals(orderOperation.calculateShippingDiscount(order), 0.0);
        orderOperation.addProductToCart(order, "Keyboard");
        assertEquals(orderOperation.calculateDeskLampDiscount(order), 0.0);
        assertEquals(orderOperation.calculateKeyboardDiscount(order), 4.099);
        assertEquals(orderOperation.calculateShippingDiscount(order), 10.0);
        orderOperation.addProductToCart(order, "Monitor");
        orderOperation.addProductToCart(order, "Monitor");
        orderOperation.addProductToCart(order, "Monitor");
        orderOperation.addProductToCart(order, "Monitor");
        orderOperation.addProductToCart(order, "Desklamp");
        assertEquals(orderOperation.calculateDeskLampDiscount(order), 44.995);
        assertEquals(orderOperation.calculateKeyboardDiscount(order), 4.099);
        assertEquals(orderOperation.calculateShippingDiscount(order), 10.0);
        orderOperation.addProductToCart(order, "Desklamp");
        assertEquals(orderOperation.calculateDeskLampDiscount(order), 89.99);
        assertEquals(orderOperation.calculateKeyboardDiscount(order), 4.099);
        assertEquals(orderOperation.calculateShippingDiscount(order), 10.0);
}}
