package models;

import java.util.ArrayList;
import java.util.List;

public class Furnisher {
    private int furnisher_id;
    private String furnisher_name;
    private String furnisher_adress;
    private String furnisher_phone;
    private List<Product> products;

    // Constructors
    public Furnisher() {
        this.products = new ArrayList<>();
    }

    public Furnisher(String furnisher_name, String address, String furnisher_phone, List<Product> products) {
        super();
        this.furnisher_name = furnisher_name;
        this.furnisher_adress = address;
        this.furnisher_phone = furnisher_phone;
        this.products = products != null ? products : new ArrayList<>();
    }

    // Setters & Getters
    public String getName() {
        return furnisher_name;
    }

    public void setName(String furnisher_name) {
        this.furnisher_name = furnisher_name;
    }

    public String getAdress() {
        return furnisher_adress;
    }

    public void setAdress(String address) {
        this.furnisher_adress = address;
    }

    public String getPhone() {
        return furnisher_phone;
    }

    public void setPhone(String furnisher_phone) {
        this.furnisher_phone = furnisher_phone;
    }

    public List<Product> getProducts() {
        return products;
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
