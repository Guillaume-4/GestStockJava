package models;

public class Category {
    private int category_id;
    private String category_name;

    // Constructor
    public Category(String category_name) {
        super();
        this.category_name = category_name;
    }

    // Setters & Getters
    public String getName() {
        return category_name;
    }

    public void setName(String category_name) {
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
