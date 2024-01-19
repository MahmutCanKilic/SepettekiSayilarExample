package game.sprite;

import java.awt.*;
import java.util.Random;

public class Meyve extends Sprite {
    Color renk;
    public Meyve() {
        Random random = new Random();
        x = random.nextInt(400);  // Pencere genişliği
        y = 0;
        speed = random.nextInt(5) + 1;  // Düşüş hızı
    }

    public void move() {
        y += speed;
    }

    public void initFruit(Graphics g) {
        g.setColor(renk);
        g.fillOval(x, y, 20, 20);  // Meyve boyutu
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}