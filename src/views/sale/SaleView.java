package views.sale;

import javax.swing.*;
import java.util.List;


import models.Client;
import models.Product;
import models.DAO.ClientDAO;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SaleView {
    private JFrame frame;
    private JTextField quantityTxtField;
    private JTextField dateTxtField;
    private JTextField productIDTxtField;
    private JButton addSaleBtn;
    private Client client;

    // Constructor
    @SuppressWarnings("deprecation")
    public SaleView() {
        frame = new JFrame("Ajout d'un Produit");
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        JLabel quantityLabel = new JLabel("Quantité :");
        quantityTxtField = new JTextField(15);
        JLabel dateLabel = new JLabel("Date :");
        dateTxtField = new JTextField(15);
        JLabel productIDLabel = new JLabel("ProductID :");
        productIDTxtField = new JTextField(15);
        JLabel Client = new JLabel("Client :");
        String[] ClientNames = new String[client.size()];
            for (int i = 0; i < client.size(); i++)
                ClientNames[i] = client.get(i).getClient_name();

            String ClientName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le Client à modifier :",
                    "Modification de Client",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ClientNames,
                    ClientNames[0]);

            if (ClientName == null)
                return;

            Client Client2 = new ClientDAO().getclientByName(ClientName);

            if (Client2 == null) {
                JOptionPane.showMessageDialog(null, "Le Client " + ClientName + " n'a pas été trouvé !");
                return;
            }

        addSaleBtn = new JButton("Ajouter");

        frame.add(quantityLabel);
        frame.add(quantityTxtField);
        frame.add(dateLabel);
        frame.add(dateTxtField);
        frame.add(productIDLabel);
        frame.add(productIDTxtField);
        frame.add(addSaleBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    // Getters
    public int getQuantity() {
        return Integer.parseInt(quantityTxtField.getText(), 10);
    }

    public Date getDate() {
        return Date.valueOf(dateTxtField.getText());
    }

    public int getProductID() {
        return Integer.parseInt(productIDTxtField.getText(), 10);
    }

    // Setters
    public void setAddSaleListener(ActionListener listener) {
        addSaleBtn.addActionListener(listener);
    }
}
