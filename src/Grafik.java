import javax.swing.*;
import java.awt.*;

public class Grafik extends Canvas {

    //MoneyCounter
    int MC = 0;

    //TotalMoneyEarnedCounter
    int TME = 0;

    //ActivePowerUp  O = Owned
    int APUp1 = 10;
    int OAPUp1 = 0;
    int APUp2 = 1000;
    int OAPUp2 = 0;

    //PassivePowerUp  O = Owned
    int PPUp1 = 100;
    int OPPUp1 = 0;
    int PPUp2 = 10000;
    int OPPUp2 = 0;

    //IncomePerClick
    int IPC = 1;

    //IncomePerSecond
    int IPS = 0;

    //Egg
    int Egg = 1000000;

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

        //Background
        DrawRectangle(0, 0, 1920, 1080, new Color(190, 175, 225), g);

        //Click box
        DrawRectangle(475, 500, 250, 150, new Color(0, 0, 0), g);

        //Passive upgrades (Left)
        DrawRectangle(100, 100, 200, 145, new Color(0, 0, 0), g);
        DrawRectangle(100, 275, 200, 145, new Color(0, 0, 0), g);

        //Active upgrades (Right)
        DrawRectangle(900, 100, 200, 145, new Color(0, 0, 0), g);
        DrawRectangle(900, 275, 200, 145, new Color(0, 0, 0), g);

        //Money and total clicks (Left)
        DrawRectangle(50, 525, 300, 50, new Color(0, 0, 0), g);
        DrawRectangle(50, 600, 300, 50, new Color(0, 0, 0), g);

        //Money per click and per second (Right)
        DrawRectangle(850, 525, 300, 50, new Color(0, 0, 0), g);
        DrawRectangle(850, 600, 300, 50, new Color(0, 0, 0), g);

        //Easter Egg (Hidden:P)
        DrawRectangle(1400, 800, 120, 90, new Color(0, 0, 0), g);


        DrawText(500, 590, g, "Click Here!", 40);

        DrawText(125, 140, g, "A Helping Hand", 20);
        DrawText(125, 180, g, "Price: " + PPUp1, 20);
        DrawText(125, 220, g, "Owned: " + OPPUp1, 20);

        DrawText(110, 310, g, "Another Helping Hand", 18);
        DrawText(110, 350, g, "Price: " + PPUp2, 20);
        DrawText(110, 390, g, "Owned: " + OPPUp2, 20);

        DrawText(925, 140, g, "A Pair of Gloves", 20);
        DrawText(925, 180, g, "Price: " + APUp1, 20);
        DrawText(925, 220, g, "Owned: " + OAPUp1, 20);

        DrawText(935, 310, g, "Bigger Gloves", 20);
        DrawText(935, 350, g, "Price: " + APUp2, 20);
        DrawText(935, 390, g, "Owned: " + OAPUp2, 20);

        DrawText(60, 555, g, "Money: " + MC, 20);
        DrawText(60, 630, g, "Total Earned: " + TME, 20);

        DrawText(860, 555, g, "Income/Click: " + IPC, 20);
        DrawText(860, 630, g, "Income/Second: " + IPS, 20);

        DrawText(1410, 830, g, "Easter Egg", 20);
        DrawText(1415, 870, g, "(Click me)", 20);

    }
    public void DrawRectangle(int x, int y, int w, int h, Color c, Graphics g){
        g.setColor(c);
        g.fillRect(x, y, w, h);
    }
    public void DrawText(int x, int y,Graphics g,String t, int s) {
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("TimesRoman", Font.PLAIN, s));
        g.drawString(t, x, y);
    }
}