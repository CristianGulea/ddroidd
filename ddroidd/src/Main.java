import model.Order;
import model.ShoppingData;
import utils.ProductException;
import utils.OrderOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        String menu = "-----------\nOption 1: Display Products. \nOption 2: Shopping.\nOption 3: Generate detailed invoice.\nOption 4: Show special offers.\nOption 5: Exit.\nPlease select a number... :)\n-----------";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ShoppingData shoppingData = new ShoppingData();
        Order order = new Order();
        OrderOperation cartOperation = new OrderOperation(shoppingData);
        while(true) {
            System.out.println(menu);
            try {
                System.out.println("Your options is: ");
                String option = reader.readLine();
                switch (option) {
                    case "1" -> {
                        System.out.println("---------Catalog----------");
                        shoppingData.getProducts().forEach((s, product) -> System.out.println(s + " - $" + product.getPrice()));
                    }
                    case "5" -> {
                        System.out.println("Bye.. :)");
                        return;
                    }
                    case "2" -> {
                        System.out.println("Please introduce the name of a product: ");
                        String productName = reader.readLine();
                        //capitalize the string
                        if (! productName.isEmpty()) productName = productName.substring(0, 1).toUpperCase() + productName.substring(1).toLowerCase(Locale.ROOT);
                        cartOperation.addProductToCart(order, productName);
                        System.out.println("Shopping cart contents:");
                        order.getCart().forEach((s, productIntegerTuple) -> System.out.println(s + " x " + productIntegerTuple.getRight()));
                    }
                    case "3" -> {
                        System.out.println("Shopping cart contents:");
                        order.getCart().forEach((s, productIntegerTuple) -> System.out.println(s + " x " + productIntegerTuple.getRight()));
                        System.out.println("\nInvoice:");
                        System.out.println("Subtotal: " + order.getSubtotal() + "$\nShipping: " + order.getShipping() + "$\nVAT: " + order.getVAT() + "$");
                        String discount = "";
                        double deskLampDiscountValue = cartOperation.calculateDeskLampDiscount(order);
                        double keyboardDiscountValue = cartOperation.calculateKeyboardDiscount(order);
                        double shippingDiscountValue = cartOperation.calculateShippingDiscount(order);
                        if (deskLampDiscountValue != 0)
                            discount += "50% off desk lamp: -" + deskLampDiscountValue + "$\n";
                        if (keyboardDiscountValue != 0)
                            discount += "10% off keyboard: -" + keyboardDiscountValue + "$\n";
                        if (shippingDiscountValue != 0)
                            discount += "$10 off shipping: -" + shippingDiscountValue + "$\n";
                        if (! discount.isEmpty())
                            System.out.println("\nDiscounts: \n" + discount);
                        System.out.println("Total: " + (order.getShipping() + order.getSubtotal() + order.getVAT() - deskLampDiscountValue - keyboardDiscountValue - shippingDiscountValue)+ "$");
                    }
                    case "4" -> {
                        String specialOffers = "Special offers are:\n-> Keyboards are 10% off \n-> Buy 2 Monitors and get a desk lamp at half price\n-> Buy any 2 items or more and get a maximum of $10 off shipping fees";
                        System.out.println(specialOffers);
                    }
                    default -> System.out.println(option + " is not an option...");
                }
            } catch (IOException | ProductException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
