package game;

import game.sprite.Sprite;

import javax.swing.*;
import java.awt.*;

public class Particle extends Sprite {
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int life;
    private Color color;
    private long creationTime;
    private float size;
    private int width;
    public Particle(int x, int y, int speedX, int speedY, int life, Color color, float size) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.life = life;
        this.color = color;
        this.creationTime = System.currentTimeMillis();
        this.size = size;
        initParticle();
    }
    public void initParticle(){
        var particleImg = "src/images/particle.jpg";
        var ii = new ImageIcon(particleImg);
        setImage(ii.getImage());
    }
    public long getCreationTime() {
        return creationTime;
    }
    public boolean isAlive() {
        return life > 0;
    }

    public void update() {
        x += speedX;
        y += speedY;
        life--;
        size -= 0.1;

        if (size <= 0) {
            size = 0;
        }

    }

    public void draw(Graphics g) {
        g.drawImage(getImage(), x, y, null);
    }
}

