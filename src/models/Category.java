package models;

public class Category {
    private int category_id;
    private String category_name;

    // Constructor
    public Category(String category_name) {
        super();
        this.category_name = category_name;
    }

    // Getters
    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    // Setters
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    // Functions
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Category {" +
                "category_id=" + category_id +
                ", category_name='" + category_name + "'" +
                "}";
    }
}
