package models;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_id;
    private String product_name;
    private int product_quantity;
    private double product_unit_price;
    private Category category;
    private Furnisher furnisher;
    private List<Sale> sales;

    // Constructors
    public Product() {
        this.sales = new ArrayList<>();
    }

    public Product(
            int product_id,
            String product_name,
            int product_quantity,
            double product_unit_price,
            Category category,
            Furnisher furnisher,
            List<Sale> sales) {
        super();
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_unit_price = product_unit_price;
        this.category = category;
        this.furnisher = furnisher;
        this.sales = sales != null ? sales : new ArrayList<>();
    }

    // Getters
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public double getProduct_unit_price() {
        return product_unit_price;
    }

    public Category getCategory() {
        return category;
    }

    public Furnisher getFurnisher() {
        return furnisher;
    }

    public List<Sale> getSales() {
        return sales;
    }

    // Setters
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public void setProduct_unit_price(double product_unit_price) {
        this.product_unit_price = product_unit_price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setFurnisher(Furnisher furnisher) {
        this.furnisher = furnisher;
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
