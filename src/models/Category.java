package models;

public class Category {
    private int category_id;
    private String category_name;

    // Constructor
    public Category(String category_name) {
        this.category_name = category_name;
    }

    public Category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    // Getters
    public int getCategoryId() {
        return category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    // Setters
    public void setCategoryName(String category_name) {
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
