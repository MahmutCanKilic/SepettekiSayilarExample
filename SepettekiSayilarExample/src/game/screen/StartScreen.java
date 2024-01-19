package game.screen;

import game.Commons;
import game.SepettekiSayilar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartScreen extends JPanel {

    public StartScreen(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(80, 0, 0, 0);
        JLabel startScreen = getStartButton();
        add(startScreen , gbc);
    }

    private JLabel getStartButton() {
        ImageIcon startButtonImage = new ImageIcon( "C:\\refactor\\SepettekiSayilarExample\\SepettekiSayilarExample\\src\\images\\baslat2.png");
        JLabel startButtonLabel = new JLabel(startButtonImage);
        startButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButtonLabel.setIcon(startButtonImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButtonLabel.setIcon(startButtonImage);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                var parent = (SepettekiSayilar) SwingUtilities.getWindowAncestor(StartScreen.this);
                parent.cardLayout.show(parent.cardPanel, "game_screen");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startButtonLabel.setIcon(startButtonImage);
            }
        });
        return startButtonLabel;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Arka plan resmini çiz
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT));
        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        ImageIcon background = new ImageIcon("C:\\refactor\\SepettekiSayilarExample\\SepettekiSayilarExample\\src\\images\\background.png");
        Image backgroundImage = background.getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT));
    }
}

