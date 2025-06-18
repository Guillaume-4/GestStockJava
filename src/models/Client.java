package models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int client_id;
    private String client_name;
    private String client_address;
    private String client_complement;
    private String client_zipcode;
    private String client_city;
    private String client_country;
    private String client_phone;

    public Client(String client_name, String client_address, String client_complement, String client_zipcode, String client_city, String client_country, String client_phone) {
        this.client_name = client_name;
        this.client_address = client_address;
        this.client_complement = client_complement;
        this.client_zipcode = client_zipcode;
        this.client_city = client_city;
        this.client_country = client_country;
        this.client_phone = client_phone;
    }

    //getter & setter

    public Client(int client_id2, String name, String address, String complement, String zipcode, String city,
            String country, String phone) {
        //TODO Auto-generated constructor stub
    }

    public String getClient_address() {
        return client_address;
    }

    public String getClient_city() {
        return client_city;
    }

    public String getClient_complement() {
        return client_complement;
    }

    public String getClient_country() {
        return client_country;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public String getClient_zipcode() {
        return client_zipcode;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public void setClient_city(String client_city) {
        this.client_city = client_city;
    }

    public void setClient_complement(String client_complement) {
        this.client_complement = client_complement;
    }

    public void setClient_country(String client_country) {
        this.client_country = client_country;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setClient_zipcode(String client_zipcode) {
        this.client_zipcode = client_zipcode;
    }

    //ToString

    @Override
    public String toString() {
        return "client {" +
                "client_id=" + client_id +
                ", client_name='" + client_name + "'" +
                ", client_address='" + client_address + "'" +
                ", complement=" + client_complement +
                ", zipcode=" + client_zipcode +
                ", city=" + client_city +
                ", country=" + client_country +
                ", phone=" + client_phone +
                "}";
    }

    public Client get(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
