package models;

import java.util.Date;

public class Sale {
    private int sale_id;
    private Product product;
    private int sale_quantity;
    private Date sale_date;

    // Constructor
    public Sale(Product product, int sale_quantity, Date sale_date) {
        super();
        this.product = product;
        this.sale_quantity = sale_quantity;
        this.sale_date = sale_date;
    }

    // Setters & Getters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return sale_quantity;
    }

    public void setQuantity(int sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public Date getDate() {
        return sale_date;
    }

    public void setDate(Date sale_date) {
        this.sale_date = sale_date;
    }

    // Functions
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Sale {" +
                "sale_id=" + sale_id +
                ", product=" + product +
                ", sale_quantity=" + sale_quantity +
                ", sale_date='" + sale_date + "'" +
                "}";
    }
}
