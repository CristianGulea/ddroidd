package model;

/**
 * Define a product.
 */
public class Product {
    private String name;
    private double price;
    private String shippedFrom;
    private double weight;

    public Product(String name, double price, String shippedFrom, double weight) {
        this.name = name;
        this.price = price;
        this.shippedFrom = shippedFrom;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShippedFrom() {
        return shippedFrom;
    }

    public void setShippedFrom(String shippedFrom) {
        this.shippedFrom = shippedFrom;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if ((obj == null) || (obj.getClass() != this.getClass())) return false;
        Product product = (Product) obj;
        return this.getPrice() ==  product.getPrice() && (this.name.equals(product.getName())) &&
                (this.getShippedFrom().equals(product.getShippedFrom())) && (this.getWeight() == product.getWeight());
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", shippedFrom='" + shippedFrom + '\'' +
                ", weight=" + weight +
                '}';
    }
}
