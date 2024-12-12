package models;

import java.util.ArrayList;
import java.util.List;

public class Furnisher {
    private int furnisher_id;
    private String furnisher_name;
    private String furnisher_adress;
    private String furnisher_complement;
    private String furnisher_zipcode;
    private String furnisher_city;
    private String furnisher_country;
    private String furnisher_phone;
    private List<Product> products;

    // Constructors
    public Furnisher() {
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
        super();
        this.furnisher_name = furnisher_name;
        this.furnisher_adress = furnisher_address;
        this.furnisher_complement = furnisher_complement;
        this.furnisher_zipcode = furnisher_zipcode;
        this.furnisher_city = furnisher_city;
        this.furnisher_country = furnisher_country;
        this.furnisher_phone = furnisher_phone;
        this.products = products != null ? products : new ArrayList<>();
    }

    // Getters
    public int getFurnisher_id() {
        return furnisher_id;
    }

    public String getFurnisher_name() {
        return furnisher_name;
    }

    public String getFurnisher_adress() {
        return furnisher_adress;
    }

    public String getFurnisher_complement() {
        return furnisher_complement;
    }

    public String getFurnisher_zipcode() {
        return furnisher_zipcode;
    }

    public String getFurnisher_city() {
        return furnisher_city;
    }

    public String getFurnisher_country() {
        return furnisher_country;
    }

    public String getFurnisher_phone() {
        return furnisher_phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    // Setters
    public void setFurnisher_name(String furnisher_name) {
        this.furnisher_name = furnisher_name;
    }

    public void setFurnisher_adress(String furnisher_adress) {
        this.furnisher_adress = furnisher_adress;
    }

    public void setFurnisher_complement(String furnisher_complement) {
        this.furnisher_complement = furnisher_complement;
    }

    public void setFurnisher_zipcode(String furnisher_zipcode) {
        this.furnisher_zipcode = furnisher_zipcode;
    }

    public void setFurnisher_city(String furnisher_city) {
        this.furnisher_city = furnisher_city;
    }

    public void setFurnisher_country(String furnisher_country) {
        this.furnisher_country = furnisher_country;
    }

    public void setFurnisher_phone(String furnisher_phone) {
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
                ", furnisher_adress='" + furnisher_adress + "'" +
                ", products=" + products +
                "}";
    }
}
