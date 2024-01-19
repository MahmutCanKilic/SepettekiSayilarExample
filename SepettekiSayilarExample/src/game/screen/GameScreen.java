package game.screen;

import game.sprite.Meyve;
import game.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen extends JPanel implements KeyListener {

    private java.util.List<Meyve> meyveler = new ArrayList<>();
    private Player player = new Player();
    private int skor = 0;
    private boolean oyunDevamEdiyor = true;

    public GameScreen() {
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oyunDevamEdiyor) {
                    update();
                    repaint();
                }
            }
        });
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        requestFocus();

    }

    private void update() {
        for (Meyve meyve : meyveler) {
            meyve.move();
            if (meyve.getBounds().intersects(player.getBounds())) {
                meyveler.remove(meyve);

                createExplosion(meyve.getX(),meyve.getY(),15,Color.red, 5);
                skor++;
                break;
            }
        }
        meyveleriEkle();
        meyveCikar();

        // Sepetin sağ veya sol köşesine çarptığını kontrol et
        if (player.getX() <= 0 || player.getX() + player.getBounds().getWidth() >= getWidth()) {
            oyunDevamEdiyor = false;
            JOptionPane.showMessageDialog(this, "Oyun bitti! Skor: " + skor, "Oyun Sonu", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        this.requestFocusInWindow();
    }

    private void meyveleriEkle() {
        if (Math.random() < 0.05) {  // Yeni meyve eklemek için olasılık
            meyveler.add(new Meyve());
        }
    }

    private void meyveCikar() {
        Iterator<Meyve> iterator = meyveler.iterator();
        while (iterator.hasNext()) {
            Meyve meyve = iterator.next();
            if (meyve.getY() > getHeight()) {
                iterator.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        player.draw(g);

        for (Meyve meyve : meyveler) {
            meyve.initFruit(g);
        }

        // Skoru çiz
        g.setColor(Color.BLACK);
        g.drawString("Skor: " + skor, 10, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.hareketEt(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.hareketEt(5);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}