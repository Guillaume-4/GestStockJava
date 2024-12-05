package models;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_id;
    private String product_name;
    private int product_quantity;
    private double product_unit_price;
    private Furnisher furnisher;
    private List<Sale> sales;

    // Constructors
    public Product() {
        this.sales = new ArrayList<>();
    }

    public Product(String product_name, int product_quantity, double product_unit_price, Furnisher furnisher,
            List<Sale> sales) {
        super();
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_unit_price = product_unit_price;
        this.furnisher = furnisher;
        this.sales = sales != null ? sales : new ArrayList<>();
    }

    // Setters & Getters
    public String getName() {
        return product_name;
    }

    public void setName(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return product_quantity;
    }

    public void setQuantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public double getUnitPrice() {
        return product_unit_price;
    }

    public void setUnitPrice(double product_unit_price) {
        this.product_unit_price = product_unit_price;
    }

    public Furnisher getFurnisher() {
        return furnisher;
    }

    public void setFurnisher(Furnisher furnisher) {
        this.furnisher = furnisher;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales != null ? sales : new ArrayList<>();
    }

    // Functions
    public void afficher() {
        System.out.println(this.toString());
    }

    public boolean isSoldOut() {
        return product_quantity == 0;
    }

    @Override
    public String toString() {
        return "Product {" +
                "product_id=" + product_id +
                ", product_name='" + product_name + "'" +
                ", product_quantity=" + product_quantity +
                ", product_unit_price=" + product_unit_price +
                ", furnisher=" + furnisher +
                ", sales=" + sales +
                "}";
    }
}
