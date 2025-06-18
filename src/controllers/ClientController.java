package controllers;

import javax.swing.JOptionPane;

import models.Client;
import models.DAO.ClientDAO;
import views.client.ClientView;

public class ClientController {
    private ClientView view;
    private ClientDAO ClientDAO;

    public ClientController(ClientView view, ClientDAO ClientDAO) {
        this.view = view;
        this.ClientDAO = ClientDAO;

        this.view.setclientListener(e -> {
            String name = view.getclientName();
            String address = view.getclientAdress();
            String complement = view.getclientComplement();
            String zipcode = view.getclientZipCode();
            String city = view.getclientCity();
            String phone = view.getclientPhone();
            String country = view.getclientCountry();

            if (view.getclient() != null) {
                Client Client = new Client(view.getclient().getClient_id(), name, address, complement, zipcode,
                city, country, phone);

                models.DAO.ClientDAO.updateclient(Client);

                JOptionPane.showMessageDialog(null, "Client modifié !");
            } else {
                Client Client = new Client(name, address, complement, zipcode, city, country, phone);

                models.DAO.ClientDAO.addclient(Client);

                JOptionPane.showMessageDialog(null, "Client ajouté !");
            }
        });
    }
}
