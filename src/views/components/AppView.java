package views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppView extends JFrame {
    private String title;
    protected GridBagConstraints gbc;
    protected JPanel contentPanel;

    public AppView(String title, int width, int height, boolean resizable) {
        super("GestStock - " + title);
        this.title = title;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setResizable(resizable);
        setLocationRelativeTo(null);

        contentPanel = new JPanel(new GridBagLayout());
        setContentPane(contentPanel);

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
        contentPanel.add(titleLabel, gbc);

        // Empty Space
        addEmptySpace(x, y + 1, 10);
    }

    public void addEmptySpace(int x, int y, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        contentPanel.add(Box.createRigidArea(new Dimension(0, height)), gbc);
    }
}
