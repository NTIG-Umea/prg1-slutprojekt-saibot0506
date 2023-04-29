import javax.swing.*;
import java.awt.*;

public class Grafik extends Canvas {

    public static void main(String[] args) {
        Grafik x = new Grafik();
    }
    public Grafik() {
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);

    }

    public void paint(Graphics g) {
        DrawSquare(475, 500, g);
        DrawAPUp1(900, 100, g);
        DrawAPUp2(900, 250, g);
        DrawPPUp1(100, 100, g);
        DrawPPUp2(100, 250, g);
        DrawMC(50, 600, g);
        DrawTMEC(850, 600, g);
    }

    public void DrawSquare(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 250, 150);
    }
    public void DrawAPUp1(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 200, 100);
    }
    public void DrawAPUp2(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 200, 100);
    }
    public void DrawPPUp1(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 200, 100);
    }
    public void DrawPPUp2(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 200, 100);
    }
    public void DrawMC(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 300, 50);
    }
    public void DrawTMEC(int x, int y, Graphics g) {
        g.setColor(new Color(000000));
        g.fillRect(x, y, 300, 50);
    }
  
}