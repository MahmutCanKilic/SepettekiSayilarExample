package game;

import game.sprite.Meyve;
import game.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board extends JPanel implements KeyListener {
//
    private java.util.List<Meyve> meyveler = new ArrayList<>();
    private Player player = new Player();
    private int skor = 0;
    private boolean oyunDevamEdiyor = true;
    private ParticleSystem particleSystem;
    private Dimension d;
    private Timer timer;
    public Board() {
        initBoard();
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

        particleSystem = new ParticleSystem();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();

    }
    private class TAdapter extends KeyAdapter {
        private Set<Integer> pressedKeys = new HashSet<>();

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.remove(keyCode);

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.add(keyCode);

            int x = player.getX();
            int y = player.getY();

        }
    }
    private void gameInit() {

        //1 tane daha player eklenecek
        player = new Player();
    }
    private void update() {
        for (Meyve meyve : meyveler) {
            meyve.move();
            if (meyve.getBounds().intersects(player.getBounds())) {
                meyveler.remove(meyve);

                createExplosion(meyve.getX(),meyve.getY(),100,Color.red, 5);
                skor++;
                break;
            }

        }
        meyveleriEkle();
        meyveCikar();
    }

    private void meyveleriEkle() {
        if (Math.random() < 0.05) {
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

        player.draw(g);
        drawExplosion(g);
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

    private void doGameCycle() {
        update();
        updateExplosion();
        repaint();
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

                doGameCycle();

        }
    }

    private void createExplosion(int x, int y, int count,Color color,float size) {
        particleSystem.createParticles(x, y, count,color,size);
    }

    private void updateExplosion() {
        particleSystem.updateParticles();
    }
    private void drawExplosion(Graphics g) {
        particleSystem.drawParticles(g);
    }
}