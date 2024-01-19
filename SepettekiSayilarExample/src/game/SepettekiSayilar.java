package game;

import javax.swing.*;
import java.awt.*;

public class SepettekiSayilar extends JFrame  {

    public SepettekiSayilar() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Sepetteki Sayilar");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args)  {

        EventQueue.invokeLater(() -> {

            var ex = new SepettekiSayilar();
            ex.setVisible(true);
        });
    }
}