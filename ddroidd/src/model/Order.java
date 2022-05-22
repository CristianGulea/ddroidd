package model;

import java.util.HashMap;

/**
 * Define an order, which contain the cart with products and the subtotal, VAT and shipping values.
 */
public class Order {
    private double shipping;
    private double subtotal;
    private double VAT;
    private HashMap<String, Tuple<Product, Integer>> cart;

    public Order() {
        this.shipping = 0;
        this.subtotal = 0;
        this.VAT = 0;
        this.cart = new HashMap<>();
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public HashMap<String, Tuple<Product, Integer>> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Tuple<Product, Integer>> cart) {
        this.cart = cart;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "shipping=" + shipping +
                ", subtotal=" + subtotal +
                ", VAT=" + VAT +
                ", cart=" + cart +
                '}';
    }
}
