package model;

import java.util.HashMap;

/**
 * Define shopping data, this class contain the information from the provided catalog of products and the shipping rates
 */
public class ShoppingData {
    private final HashMap<String, Product> products;
    private final HashMap<String, ShippingRate> shippingRates;

    public ShoppingData(){
        products = new HashMap<>(){{
            put("Mouse", new Product("Mouse", 10.99, "RO", 0.2));
            put("Keyboard", new Product("Keyboard", 40.99, "UK", 0.7));
            put("Monitor", new Product("Monitor", 164.99, "US", 1.9));
            put("Webcam", new Product("Webcam", 84.99, "RO", 0.2));
            put("Headphones", new Product("Headphones", 59.99, "US", 0.6));
            put("Desklamp", new Product("Desklamp", 89.99, "UK", 1.3));
        }};
        shippingRates = new HashMap<>(){{
            put("RO", new ShippingRate("RO", 1));
            put("UK", new ShippingRate("UK", 2));
            put("US", new ShippingRate("US", 3));
        }};
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public HashMap<String, ShippingRate> getShippingRates() {
        return shippingRates;
    }
}
