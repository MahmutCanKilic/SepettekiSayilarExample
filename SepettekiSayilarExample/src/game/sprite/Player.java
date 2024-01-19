package game.sprite;

import game.Commons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private boolean isMovingLeft, isMovingRight;
    private Image playerImage;

    public Player() {
        x = Commons.BOARD_WIDTH / 2;  // Başlangıç pozisyonu
        y = 550;  // Başlangıç pozisyonu


    //Karakter
        ImageIcon ii = new ImageIcon("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\turuncuSincap.png");
        playerImage = ii.getImage();
    }

    public void hareketEt(int deltaX) {
        x += deltaX * 4;


        if (x < 0) {
            x = 0;
        } else if (x + Commons.PLAYER_WIDTH > 750) {
            x = 780 - Commons.PLAYER_WIDTH;
        }
    }


    public void draw(Graphics g) {
        // Image'i çiz
        g.drawImage(playerImage, x, y, Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT, null);
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