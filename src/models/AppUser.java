package models;

public class AppUser {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_role;

    // Constructors
    public AppUser(String user_name, String user_password, String user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    public AppUser(int user_id, String user_name, String user_password, String user_role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    // Getters
    public int getUserId() {
        return user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserPassword() {
        return user_password;
    }

    public String getUserRole() {
        return user_role;
    }

    // Setters
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public void setUserRole(String user_role) {
        this.user_role = user_role;
    }
}
