package utils;

import model.Order;
import model.Product;
import model.ShoppingData;
import model.Tuple;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderOperation {
    private ShoppingData shoppingData;

    public OrderOperation(ShoppingData shoppingData) {
        this.shoppingData = shoppingData;
    }

    /**
     * Validate if a product name exist in shopping data.
     * @param name String, name of the product
     * @return Product, the product with the provided name
     * @throws ProductException, if the name does not exist in shopping data
     */
    private Product validateProductExistence(String name) throws ProductException {
        Product product = this.shoppingData.getProducts().get(name);
        if (product != null) return product;
        else throw new ProductException("Product " + name + " doesn't exists");
    }

    /**
     * Add a new product to the cart
     * @param order Order
     * @param name String, the name of the new product
     * @param product Product
     * @return HashMap<String, Tuple<Product, Integer>>, the cart which contain the new product
     */
    private HashMap<String, Tuple<Product, Integer>> addNewProductToCart(Order order, String name, Product product){
        Tuple<Product, Integer> productTuple = new Tuple<>(product, 1);
        HashMap<String, Tuple<Product, Integer>> newCart = order.getCart();
        newCart.put(name, productTuple);
        return newCart;
    }

    /**
     * Add a product which exist in the cart
     * @param order Order
     * @param name String, name of the product
     * @param product Product
     * @return HashMap<String, Tuple<Product, Integer>>, the cart which contain the product
     */
    private HashMap<String, Tuple<Product, Integer>> addExistingProductToCart(Order order, String name, Product product){
        HashMap<String, Tuple<Product, Integer>> newCart = order.getCart();
        Tuple<Product, Integer> productTuple = newCart.get(name);
        productTuple.setRight(productTuple.getRight() + 1);
        newCart.remove(name);
        newCart.put(name, productTuple);
        return newCart;
    }

    /**
     * Add a product to the cart and update the subtotal, VAT and shipping value
     * @param order Order
     * @param name String, the name of the product
     * @throws ProductException, if the product does not exist
     */
    public void addProductToCart(Order order, String name) throws ProductException {
        Product product = validateProductExistence(name);
        if (! order.getCart().containsKey(name))
            order.setCart(addNewProductToCart(order, name, product));
        else
            order.setCart(addExistingProductToCart(order, name, product));
        order.setSubtotal(order.getSubtotal() + product.getPrice());
        order.setShipping(order.getShipping() + calculateShipping(product.getWeight(), this.shoppingData.getShippingRates().get(product.getShippedFrom()).getRate()));
        order.setVAT(calculateVAT(order.getSubtotal()));
    }

    /**
     * Calculate the shipping value
     * @param weight double
     * @param rate double
     * @return int, the shipping fees
     */
    private int calculateShipping(double weight, double rate){
        return (int) ((weight*10) * rate);
    }

    /**
     * Calculate the VAT
     * @param subtotal double
     * @return double, the VAT value
     */
    private double calculateVAT(double subtotal){
        return ((double) 19 /100) * subtotal;
    }

    /**
     * Calculate the DeskLamp discount
     * @param order Order
     * @return double, desk lamp discount or 0 if the cart does not contain minimum 2 monitors
     */
    public double calculateDeskLampDiscount(Order order){
        if ((order.getCart().containsKey("Monitor")) && (order.getCart().containsKey("Desklamp"))){
            if (order.getCart().get("Monitor").getRight() >= 2) {
                int numberOfMonitors = order.getCart().get("Monitor").getRight();
                int numberOfDeskLamp = order.getCart().get("Desklamp").getRight();
                int multiplication = Math.min((int) (numberOfMonitors / 2), numberOfDeskLamp);
                return multiplication * this.shoppingData.getProducts().get("Desklamp").getPrice() * 0.5;
            }
        }
        return 0;
    }

    /**
     * Calculate the keyboard discount
     * @param order Order
     * @return double, keyboard discount or 0 if the cart does not contain keyboards
     */
    public double calculateKeyboardDiscount(Order order){
        if (order.getCart().containsKey("Keyboard")){
            int numberOfKeyboards = order.getCart().get("Keyboard").getRight();
            return numberOfKeyboards * this.shoppingData.getProducts().get("Keyboard").getPrice() * 0.1;
        }
        return 0;

    }

    /**
     * Calculate the shipping discount
     * @param order Order
     * @return double, shipping discount or 0 if the cart does not contain minimum 2 products
     */
    public double calculateShippingDiscount(Order order){
        if (numberOfProductFromCart(order) >= 2){
            return 10;
        }
        return 0;
    }

    /**
     * Count the number of the products from the cart
     * @param order Order
     * @return int, number of the products from the cart
     */
    private int numberOfProductFromCart(Order order){
        AtomicInteger numberOfProducts = new AtomicInteger();
        order.getCart().forEach((s, productIntegerTuple) -> numberOfProducts.addAndGet(productIntegerTuple.getRight()));
        return numberOfProducts.intValue();
    }
}
