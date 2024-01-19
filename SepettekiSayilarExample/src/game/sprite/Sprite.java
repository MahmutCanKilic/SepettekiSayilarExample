package game.sprite;

import game.Commons;

import java.awt.*;

public class Sprite {

    public boolean visible;
    private Image image;
    private boolean dying;
    int speed;
    int x;
    int y;
    int dx;
    int width;
    int height;

    public Sprite() {
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isOutOfBounds() {
        return y >= Commons.BOARD_HEIGHT || x >= Commons.BOARD_WIDTH || x <= 0;
    }

    public void move() {
        x += dx;
    }
}