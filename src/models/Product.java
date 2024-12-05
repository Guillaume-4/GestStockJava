package models;

public class Product {
    private String name;
    private int quantity;
    private double unitPrice;
    private Furnisher furnisher;

    // Constructor
    public Product(String name, int quantity, double unitPrice, Furnisher furnisher) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.furnisher = furnisher;
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Furnisher getFurnisher() {
        return furnisher;
    }

    public void setFurnisher(Furnisher furnisher) {
        this.furnisher = furnisher;
    }

    // Functions
    public void afficher() {
        // TODO : System.out.println
    }

    public boolean isSoldOut() {
        return quantity == 0;
    }
}
