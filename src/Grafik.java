import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.sql.SQLOutput;
import java.util.TimerTask;

public class Grafik extends Canvas implements Runnable{
    private BufferStrategy bs;

    private boolean running = false;
    private Thread thread;

    //MoneyCounter
    int MC = 0;

    //TotalMoneyEarnedCounter
    int TME = 0;

    //ActivePowerUp  O = Owned  I = Income
    int APUp1 = 10;
    int OAPUp1 = 0;
    int IAPUp1 = 1;
    int APUp2 = 1000;
    int OAPUp2 = 0;
    int IAPUp2 = 100;

    //PassivePowerUp
    int PPUp1 = 100;
    int OPPUp1 = 0;
    int IPPUp1 = 5;
    int PPUp2 = 10000;
    int OPPUp2 = 0;
    int IPPUp2 = 200;

    //IncomePerClick
    int IPC = 1;

    //IncomePerSecond
    int IPS = 0;

    //Egg
    int Egg = 1000000;
    int EggC = 0;

    int timer = 0;

    private Rectangle clickBox = new Rectangle(475, 500, 250, 150);
    private Rectangle left1 = new Rectangle(100, 100, 200, 145);
    private Rectangle left2 = new Rectangle(100, 275, 200, 145);
    private Rectangle right1 = new Rectangle(900, 100, 200, 145);
    private Rectangle right2 = new Rectangle(900, 275, 200, 145);
    private Rectangle egg = new Rectangle(1400, 800, 120, 90);



    public static void main(String[] args) {
        Grafik x = new Grafik();
        x.start();

    }
    public Grafik() {
        JFrame frame = new JFrame();
        frame.add(this);
        this.addMouseListener(new ML());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }
    public void draw(Graphics g) {
        g.clearRect(0,0,getWidth(),getHeight());
        //Background
        DrawRectangle(0, 0, 1920, 1080, new Color(190, 175, 225), g);

        //Click box
        DrawRectangle(clickBox.x, clickBox.y, clickBox.width, clickBox.height, new Color(0, 0, 0), g);

        //Passive upgrades (Left)
        DrawRectangle(left1.x, left1.y, left1.width, left1.height, new Color(0, 0, 0), g);
        DrawRectangle(left2.x, left2.y, left2.width, left2.height, new Color(0, 0, 0), g);

        //Active upgrades (Right)
        DrawRectangle(right1.x, right1.y, right1.width, right1.height, new Color(0, 0, 0), g);
        DrawRectangle(right2.x, right2.y, right2.width, right2.height, new Color(0, 0, 0), g);

        //Money and total clicks (Left)
        DrawRectangle(50, 525, 300, 50, new Color(0, 0, 0), g);
        DrawRectangle(50, 600, 300, 50, new Color(0, 0, 0), g);

        //Money per click and per second (Right)
        DrawRectangle(850, 525, 300, 50, new Color(0, 0, 0), g);
        DrawRectangle(850, 600, 300, 50, new Color(0, 0, 0), g);

        //Easter Egg (Expand the window)
        DrawRectangle(egg.x, egg.y, egg.width, egg.height, new Color(0, 0, 0), g);


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
        DrawText(860, 630, g, "Passive Income: " + IPS + " /S", 20);

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
    public void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Rita ut den nya bilden
        draw(g);

        g.dispose();
        bs.show();
    }
    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        double ns = 1000000000.0 / 25.0;
        double delta = 0;
        long lastTime = System.nanoTime();
        int second = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {

                if(second >= 24){
                    MC += IPS;
                    second = 0;
                }
                else {
                    second++;
                }
                // Uppdatera koordinaterna
                update();
                // Rita ut bilden med uppdaterad data
                render();
                delta--;
            }
        }
        stop();
    }
    public void update() {
    }

    public class ML implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getX() >= clickBox.x && e.getX() <= clickBox.x + clickBox.width && e.getY() >= clickBox.y && e.getY() <= clickBox.y + clickBox.height){
                System.out.println("Click!");
                MC += IPC;
                TME += IPC;
             }
            if (e.getX() >= left1.x && e.getX() <= left1.x + left1.width && e.getY() >= left1.y && e.getY() <= left1.y + left1.height) {
                if(MC >= PPUp1) {
                    System.out.println("Purchase Complete!");
                    IPS += IPPUp1;
                    IPPUp1 *= 1.05;
                    OPPUp1++;
                    MC -= PPUp1;
                    PPUp1 *= 1.15;
                    }
                    else {System.out.println("Not Enough Money");}
            }
            if(e.getX() >= left2.x && e.getX() <= left2.x + left2.width && e.getY() >= left2.y && e.getY() <= left2.y + left2.height) {
              if(MC >= PPUp2) {
                  System.out.println("Purchase Complete!");
                  IPS += IPPUp2;
                  IPPUp2 *= 1.05;
                  OPPUp2++;
                  MC -= PPUp2;
                  PPUp2 *= 1.15;
              }
              else {System.out.println("Not Enough Money");}
            }
            if(e.getX() >= right1.x && e.getX() <= right1.x + right1.width && e.getY() >= right1.y && e.getY() <= right1.y + right1.height) {
                if(MC >= APUp1) {
                    System.out.println("Purchase Complete!");
                    IPC += IAPUp1;
                    IAPUp1 *= 1.1;
                    OAPUp1++;
                    MC -= APUp1;
                    APUp1 *= 1.1;
                }
                else {System.out.println("Not Enough Money");}
            }
            if(e.getX() >= right2.x && e.getX() <= right2.x + right2.width && e.getY() >= right2.y && e.getY() <= right2.y + right2.height) {
               if (MC >= APUp2) {
                   System.out.println("Purchase Complete!");
                   IPC += IAPUp2;
                   IAPUp2 *= 1.05;
                   OAPUp2++;
                   MC -= APUp2;
                   APUp2 *= 1.15;
               }
               else {System.out.println("Not Enough Money");}
            }
            if(e.getX() >= egg.x && e.getX() <= egg.x + egg.width && e.getY() >= egg.y && e.getY() <= egg.y + egg.height) {
                if(EggC == 0){
                    System.out.println("Congratulation, Have Some Money!");
                    MC += Egg;
                    EggC++;
                }
                else {System.out.println("You Have Already Claimed This");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}