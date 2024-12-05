package models;

public class Client {
    private int client_id;
    private String client_lastname;
    private String client_firstname;
    private String client_adress;
    private String client_phone;

    // Constructor
    public Client(String client_lastname, String client_firstname, String client_adress, String client_phone) {
        super();
        this.client_lastname = client_lastname;
        this.client_firstname = client_firstname;
        this.client_adress = client_adress;
        this.client_phone = client_phone;
    }

    // Setters & Getters
    public String getLastname() {
        return client_lastname;
    }

    public void setLastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public String getFirstname() {
        return client_firstname;
    }

    public void setFirstname(String client_firstname) {
        this.client_firstname = client_firstname;
    }

    public String getAdress() {
        return client_adress;
    }

    public void setAdress(String client_adress) {
        this.client_adress = client_adress;
    }

    public String getPhone() {
        return client_phone;
    }

    public void setPhone(String client_phone) {
        this.client_phone = client_phone;
    }

    // Functions
    public void afficher() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Client {" +
                "client_id=" + client_id +
                ", client_lastname='" + client_lastname + "'" +
                ", client_firstname='" + client_firstname + "'" +
                ", client_adress='" + client_adress + "'" +
                ", client_phone='" + client_phone + "'" +
                "}";
    }
}
