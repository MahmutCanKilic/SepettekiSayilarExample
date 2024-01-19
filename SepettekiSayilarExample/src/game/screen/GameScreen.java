package game.screen;

import game.Commons;
import game.ParticleSystem;
import game.sprite.Meyve;
import game.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameScreen extends JPanel implements KeyListener {

    private java.util.List<Meyve> meyveler = new ArrayList<>();
    private Player player = new Player();
    private int skor = 0;
    private boolean oyunDevamEdiyor = true;

    // JLabel dizisi
    private JLabel[] scoreLabels = new JLabel[4];
    private ParticleSystem particleSystem;
    private Dimension d;
    private Timer timer;
//

    public GameScreen() {
        initBoard();
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT));

        String[] imagePaths = {
                Meyve.MeyveTuru.ARMUT.getImagePath(),
                Meyve.MeyveTuru.CILEK.getImagePath(),
                Meyve.MeyveTuru.ELMA.getImagePath(),
                Meyve.MeyveTuru.MUZ.getImagePath()
        };

        GridBagConstraints[] gbcArray = new GridBagConstraints[4];

        for (int i = 0; i < 4; i++) {
            gbcArray[i] = new GridBagConstraints();
            gbcArray[i].gridx = 0;
            gbcArray[i].gridy = 0;
            gbcArray[i].insets = new Insets(-560 + i * 90, 590, 0, 0);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePaths[i]).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            JLabel imageLabel = new JLabel(imageIcon);

            gbcArray[i].gridwidth = 2;
            gbcArray[i].gridheight = 2;

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(imageLabel, BorderLayout.CENTER);

            // JLabel'ı diziye ekle
            scoreLabels[i] = new JLabel("Sayı: 0");
            panel.add(scoreLabels[i], BorderLayout.SOUTH);

            add(panel, gbcArray[i]);
        }

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

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();

    }


    private void update() {
        Iterator<Meyve> iterator = meyveler.iterator();
        while (iterator.hasNext()) {
            Meyve meyve = iterator.next();
            meyve.move();
            if (meyve.getBounds().intersects(player.getBounds())) {
                iterator.remove();
                createExplosion(meyve.getX(),meyve.getY(),15,Color.red, 5);
                skor += meyve.getPuan();

                // Sayacı güncelle
                updateCounter(meyve.getPuan());
            }
        }
        meyveleriEkle();
        meyveCikar();
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

    private void updateCounter(int puan) {
        // İlgili meyve türüne göre sayacı güncelle
        switch (puan) {
            case 2:
                scoreLabels[0].setText("Sayı: " + meyveler.get(1).countArmut++);
                break;
            case 1:
                scoreLabels[1].setText("Sayı: " + meyveler.get(3).countCilek++);
                break;
            case -1:
                scoreLabels[2].setText("Sayı: " + meyveler.get(0).countElma++);
                break;
            case -2:
                scoreLabels[3].setText("Sayı: " + meyveler.get(2).countMuz++);
                break;
            default:
                System.out.println("aaaa");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setPreferredSize(new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT));
        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        ImageIcon background = new ImageIcon("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\arkaplan.png");
        Image backgroundImage = background.getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        setPreferredSize(new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT));

        player.draw(g);

        for (Meyve meyve : meyveler) {
            meyve.initFruit(g);
        }

        // Skoru çiz
        g.setColor(Color.BLACK);
        g.drawString("Skor: " + skor, 740, 20);
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

    private void createExplosion(int x, int y, int count, Color color, float size) {
        particleSystem.createParticles(x, y, count, color, size);
    }

    private void updateExplosion() {
        particleSystem.updateParticles();
    }

    private void drawExplosion(Graphics g) {
        particleSystem.drawParticles(g);
    }
}
