package game.sprite;

import game.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {


    private boolean isMovingLeft, isMovingRight;
    public Player() {
        x = 180;  // Başlangıç pozisyonu
        y = 300;  // Başlangıç pozisyonu
    }

    public void hareketEt(int deltaX) {
        x += deltaX;

        // Pencere sınırlarını kontrol et
        if (x < 0) {
            x = 0;
        } else if (x + Commons.PLAYER_WIDTH > 400) {
            x = 400 - Commons.PLAYER_WIDTH;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y,  Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT);
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            isMovingLeft = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            isMovingRight = false;
        }
    }
}