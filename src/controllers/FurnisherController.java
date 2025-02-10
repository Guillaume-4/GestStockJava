package controllers;

import javax.swing.JOptionPane;

import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.FurnisherView;

public class FurnisherController {
    private FurnisherView view;
    private FurnisherDAO furnisherDAO;

    public FurnisherController(FurnisherView view, FurnisherDAO furnisherDAO) {
        this.view = view;
        this.furnisherDAO = furnisherDAO;

        this.view.setAddFurnisherListener(e -> {
            String name = view.getFurnisherName();
            String adress = view.getFurnisherAdress();
            String complement = view.getFurnisherComplement();
            String zipCode = view.getFurnisherZipCode();
            String city = view.getFurnisherCity();
            String country = view.getFurnisherCountry();
            String phone = view.getFurnisherPhone();

            Furnisher furnisher = new Furnisher(name, adress, complement, zipCode, city, country, phone, null);

            furnisherDAO.addFurnisher(furnisher);
            JOptionPane.showMessageDialog(null, "Fournisseur ajouté !");
        });
    }
}
