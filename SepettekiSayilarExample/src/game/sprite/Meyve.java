package game.sprite;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Meyve extends Sprite {
    private int enumValue;
    private int puan;
    public int countElma, countArmut, countMuz, countCilek;
    Random random;

    public Meyve() {
        random = new Random();
        x = random.nextInt(800);  // Pencere genişliği
        y = 0;
        speed = random.nextInt(5) + 1;  // Düşüş hızı
        enumValue = random.nextInt(MeyveTuru.values().length);
        puan = MeyveTuru.values()[enumValue].getPuan();
    }

    public void move() {
        y += speed;
    }

    public void initFruit(Graphics g) {
        String imgString = MeyveTuru.values()[enumValue].getImagePath();
        Image ii = new ImageIcon(imgString).getImage();
        g.drawImage(ii, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public int getPuan() {
        return puan;
    }

    public enum MeyveTuru {
        ELMA("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\elma.png", 2),
        ARMUT("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\armut.png", 1),
        MUZ("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\muz.png", -1),
        CILEK("C:\\Users\\karam\\IdeaProjects\\SepettekiSayilarE\\SepettekiSayilarExample\\src\\images\\cilek.png", -2);

        private final String imagePath;
        private final int puan;

        MeyveTuru(String imagePath, int puan) {
            this.imagePath = imagePath;
            this.puan = puan;
        }

        public String getImagePath() {
            return imagePath;
        }

        public int getPuan() {
            return puan;
        }
    }
}
