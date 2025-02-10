package views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AppView extends JFrame {
    public String title;
    public GridBagConstraints gbc;

    public AppView(String title, int width, int height, boolean resizable) {
        super("GestStock - " + title);
        this.title = title;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setResizable(resizable);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
    }

    public void addTitleComponent(int x, int y, int gridwidth) {
        // Title
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridwidth;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel(this.title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));
        add(titleLabel, gbc);

        // Empty Space
        gbc.gridy = y + 1;
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);
    }
}
