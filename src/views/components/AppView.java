package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

public class AppView extends JFrame {
    public GridBagConstraints gbc;

    public AppView(String title, int width, int height, boolean resizable) {
        super("GestStock - " + title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setResizable(resizable);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
    }
}
