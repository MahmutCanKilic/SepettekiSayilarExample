package game;

import game.screen.GameScreen;
import game.screen.StartScreen;

import javax.swing.*;
import java.awt.*;

public class SepettekiSayilar extends JFrame  {

    public JPanel cardPanel;
    public CardLayout cardLayout;
    public StartScreen startScreen;

    public GameScreen gameScreen;

    public SepettekiSayilar() {

        initUI();
    }

    private void initUI() {
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        //Ekranlar
        startScreen = new StartScreen();
        gameScreen = new GameScreen();

        addScreensToCardPanel();
        add(cardPanel);

        setTitle("Sepetteki Sayilar");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    private void addScreensToCardPanel() {
        cardPanel.add(startScreen, "start_screen");
        cardPanel.add(gameScreen , "game_screen");
    }

    public static void main(String[] args)  {

        EventQueue.invokeLater(() -> {

            var ex = new SepettekiSayilar();
            ex.setVisible(true);
        });
    }
}