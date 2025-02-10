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

            setUIFont(subtitleFont, "Label.font");
            setUIFont(uiFont, "Button.font", "TextField.font", "PasswordField.font", "TitledBorder.font",
                    "OptionPane.messageFont", "OptionPane.buttonFont");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start Application
        new LoginView();
    }

    private static void setUIFont(Font font, String... components) {
        for (String component : components)
            UIManager.put(component, font);
    }
}