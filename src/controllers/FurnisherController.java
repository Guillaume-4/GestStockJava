package controllers;

import javax.swing.JOptionPane;

import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.furnisher.FurnisherView;

public class FurnisherController {
    private FurnisherView view;
    private FurnisherDAO FurnisherDAO;

    public FurnisherController(FurnisherView view, FurnisherDAO FurnisherDAO) {
        this.view = view;
        this.FurnisherDAO = FurnisherDAO;

        this.view.setFurnisherListener(e -> {
            String name = view.getFurnisherName();
            String address = view.getFurnisherAdress();
            String complement = view.getFurnisherComplement();
            String zipcode = view.getFurnisherZipCode();
            String city = view.getFurnisherCity();
            String phone = view.getFurnisherPhone();
            String country = view.getFurnisherCountry();

            if (view.getFurnisher() != null) {
                Furnisher Furnisher = new Furnisher(view.getFurnisher().getFurnisherId(), name, address, complement, zipcode,
                city, country, phone);

                models.DAO.FurnisherDAO.updateFurnisher(Furnisher);

                JOptionPane.showMessageDialog(null, "Fournisseur modifié !");
            } else {
                Furnisher Furnisher = new Furnisher(name, address, complement, zipcode, city, country, phone);

                models.DAO.FurnisherDAO.addFurnisher(Furnisher);

                JOptionPane.showMessageDialog(null, "Fournisseur ajouté !");
            }
        });
    }
}
