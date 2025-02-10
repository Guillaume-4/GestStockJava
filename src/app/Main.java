package app;

import java.awt.Font;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import views.LoginView;

public class Main {
    public static void main(String[] args) {
        // Set Custom Theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            String font = "SansSerif";

            Font uiFont = new Font(font, Font.PLAIN, 14);
            Font subtitleFont = new Font(font, Font.BOLD, 14);

            UIManager.put("Label.font", subtitleFont);
            UIManager.put("Button.font", uiFont);
            UIManager.put("TextField.font", uiFont);
            UIManager.put("PasswordField.font", uiFont);
            UIManager.put("TitledBorder.font", uiFont);
            UIManager.put("OptionPane.messageFont", uiFont);
            UIManager.put("OptionPane.buttonFont", uiFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start Application
        new LoginView();
    }
}