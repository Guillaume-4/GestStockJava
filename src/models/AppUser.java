package models;

public class AppUser {
    private int user_id;
    private String user_name;
    private String user_password;
    private int role_id;

    // Constructors
    public AppUser(String user_name, String user_password, int role_id) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.role_id = role_id;
    }

    public AppUser(int user_id, String user_name, String user_password, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.role_id = role_id;
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

    public int getUserRole() {
        return role_id;
    }

    // Setters
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public void setUserRole(int role_id) {
        this.role_id = role_id;
    }
}
