package model;

/**
 * Define a shipping rate.
 */
public class ShippingRate {
    private String country;
    private double rate;

    public ShippingRate(String country, double rate) {
        this.country = country;
        this.rate = rate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ShippingRate{" +
                "country='" + country + '\'' +
                ", rate=" + rate +
                '}';
    }
}
