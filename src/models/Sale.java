package models;

import java.util.Date;

public class Sale {
    private int sale_id;
    private int sale_quantity;
    private Date sale_date;
    private Product product;

    // Constructor
    public Sale(int sale_quantity, Date sale_date, Product product) {
        super();
        this.sale_quantity = sale_quantity;
        this.sale_date = sale_date;
        this.product = product;
    }

    // Getters
    public int getSale_id() {
        return sale_id;
    }

    public int getSale_quantity() {
        return sale_quantity;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public Product getProduct() {
        return product;
    }

    // Setters
    public void setSale_quantity(int sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public void setProduct(Product product) {
        this.product = product;
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
