package controllers;

import javax.swing.JOptionPane;

import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.FurnisherView;

public class FurnisherController {
    private FurnisherView vue;
    private FurnisherDAO furnisherDAO;

    public FurnisherController(FurnisherView vue, FurnisherDAO furnisherDAO) {
        this.vue = vue;
        this.furnisherDAO = furnisherDAO;

        this.vue.setAddFurnisherListener(e -> {
            String name = vue.getFurnisherName();
            String adress = vue.getFurnisherAdress();
            String complement = vue.getFurnisherComplement();
            String zipCode = vue.getFurnisherZipCode();
            String city = vue.getFurnisherCity();
            String country = vue.getFurnisherCountry();
            String phone = vue.getFurnisherPhone();

            Furnisher furnisher = new Furnisher(name, adress, complement, zipCode, city, country, phone, null);

            furnisherDAO.addFurnisher(furnisher);
            JOptionPane.showMessageDialog(null, "Fournisseur ajouté !");
        });
    }
}
