package models;

public class Role {
    private int role_id;
    private String role_name;

    // Constructors
    public Role(String role_name) {
        this.role_name = role_name;
    }

    public Role(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    // Getters
    public int getRoleId() {
        return role_id;
    }

    public String getRoleName() {
        return role_name;
    }

    // Setters
    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    // Functions
    public String productDetails() {
        return "Role Name : " + role_name;
    }

    @Override
    public String toString() {
        return "Product {" +
                "role_id=" + role_id +
                ", role_name='" + role_name + "'" +
                "}";
    }
}
