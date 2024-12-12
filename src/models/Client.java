package models;

public class Client {
    private int client_id;
    private String client_lastname;
    private String client_firstname;
    private String client_adress;
    private String client_complement;
    private String client_zipcode;
    private String client_city;
    private String client_country;
    private String client_phone;

    // Constructor
    public Client(
            String client_lastname,
            String client_firstname,
            String client_adress,
            String client_complement,
            String client_zipcode,
            String client_city,
            String client_country,
            String client_phone) {
        super();
        this.client_lastname = client_lastname;
        this.client_firstname = client_firstname;
        this.client_adress = client_adress;
        this.client_complement = client_complement;
        this.client_zipcode = client_zipcode;
        this.client_city = client_city;
        this.client_country = client_country;
        this.client_phone = client_phone;
    }

    // Getters
    public int getClient_id() {
        return client_id;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public String getClient_firstname() {
        return client_firstname;
    }

    public String getClient_adress() {
        return client_adress;
    }

    public String getClient_complement() {
        return client_complement;
    }

    public String getClient_zipcode() {
        return client_zipcode;
    }

    public String getClient_city() {
        return client_city;
    }

    public String getClient_country() {
        return client_country;
    }

    public String getClient_phone() {
        return client_phone;
    }

    // Setters
    public void setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public void setClient_firstname(String client_firstname) {
        this.client_firstname = client_firstname;
    }

    public void setClient_adress(String client_adress) {
        this.client_adress = client_adress;
    }

    public void setClient_complement(String client_complement) {
        this.client_complement = client_complement;
    }

    public void setClient_zipcode(String client_zipcode) {
        this.client_zipcode = client_zipcode;
    }

    public void setClient_city(String client_city) {
        this.client_city = client_city;
    }

    public void setClient_country(String client_country) {
        this.client_country = client_country;
    }

    public void setClient_phone(String client_phone) {
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
