package models;

import java.util.ArrayList;
import java.util.List;

public class Furnisher {
    private int furnisher_id;
    private String furnisher_name;
    private String furnisher_address;
    private String furnisher_complement;
    private String furnisher_zipcode;
    private String furnisher_city;
    private String furnisher_country;
    private String furnisher_phone;
    private List<Product> products;

    // Constructors
    public Furnisher(String name, int quantity, double unitPrice, Category category, Furnisher furnisher, Object object) {
        this.products = new ArrayList<>();
    }

    public Furnisher(
            String furnisher_name,
            String furnisher_address,
            String furnisher_complement,
            String furnisher_zipcode,
            String furnisher_city,
            String furnisher_country,
            String furnisher_phone,
            List<Product> products) {
        this.furnisher_name = furnisher_name;
        this.furnisher_address = furnisher_address;
        this.furnisher_complement = furnisher_complement;
        this.furnisher_zipcode = furnisher_zipcode;
        this.furnisher_city = furnisher_city;
        this.furnisher_country = furnisher_country;
        this.furnisher_phone = furnisher_phone;
        this.products = products != null ? products : new ArrayList<>();
    }

    public Furnisher(
            int furnisher_id,
            String furnisher_name,
            String furnisher_address,
            String furnisher_complement,
            String furnisher_zipcode,
            String furnisher_city,
            String furnisher_country,
            String furnisher_phone) {
        this.furnisher_id = furnisher_id;
        this.furnisher_name = furnisher_name;
        this.furnisher_address = furnisher_address;
        this.furnisher_complement = furnisher_complement;
        this.furnisher_zipcode = furnisher_zipcode;
        this.furnisher_city = furnisher_city;
        this.furnisher_country = furnisher_country;
        this.furnisher_phone = furnisher_phone;
        this.products = new ArrayList<>();
    }

    public Furnisher(String furnisher_name, String furnisher_address, String furnisher_complement, String furnisher_zipcode, String furnisher_city, String furnisher_country, String furnisher_phone) {
        this.furnisher_name = furnisher_name;
        this.furnisher_address = furnisher_address;
        this.furnisher_complement = furnisher_complement;
        this.furnisher_zipcode = furnisher_zipcode;
        this.furnisher_city = furnisher_city;
        this.furnisher_country = furnisher_country;
        this.furnisher_phone = furnisher_phone;
        this.products = new ArrayList<>();
    }

    // Getters
    public int getFurnisherId() {
        return furnisher_id;
    }

    public String getFurnisherName() {
        return furnisher_name;
    }

    public String getFurnisherAdress() {
        return furnisher_address;
    }

    public String getFurnisherComplement() {
        return furnisher_complement;
    }

    public String getFurnisherZipcode() {
        return furnisher_zipcode;
    }

    public String getFurnisherCity() {
        return furnisher_city;
    }

    public String getFurnisherCountry() {
        return furnisher_country;
    }

    public String getFurnisherPhone() {
        return furnisher_phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    // Setters
    public void setFurnisherName(String furnisher_name) {
        this.furnisher_name = furnisher_name;
    }

    public void setFurnisherAdress(String furnisher_address) {
        this.furnisher_address = furnisher_address;
    }

    public void setFurnisherComplement(String furnisher_complement) {
        this.furnisher_complement = furnisher_complement;
    }

    public void setFurnisherZipcode(String furnisher_zipcode) {
        this.furnisher_zipcode = furnisher_zipcode;
    }

    public void setFurnisherCity(String furnisher_city) {
        this.furnisher_city = furnisher_city;
    }

    public void setFurnisherCountry(String furnisher_country) {
        this.furnisher_country = furnisher_country;
    }

    public void setFurnisherPhone(String furnisher_phone) {
        this.furnisher_phone = furnisher_phone;
    }

    public void setProducts(List<Product> products) {
        this.products = products != null ? products : new ArrayList<>();
    }

    // Functions
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Furnisher {" +
                "furnisher_id=" + furnisher_id +
                ", furnisher_name='" + furnisher_name + "'" +
                ", furnisher_address='" + furnisher_address + "'" +
                ", products=" + products +
                "}";
    }
}
