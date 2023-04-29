import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class Slutprojekt {
    public static void main(String[] args) {

    }

    private static void MC() {
        Scanner AIc = new Scanner(System.in);
    }

    //MoneyCounter
    int MC = 0;

    //TotalMoneyEarnedCounter
    int TMEC = 0;

    //ActivePowerUp
    int APUp1 = 0;

    int APUp2 = 0;

    //PassivePowerUp
    int PPUp1 = 0;

    int PPUp2 = 0;

    //IncomePerClick
    int IPC = 0;

    //IncomePerSecond
    int IPS = 0;


    private class ML implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            //System.out.println("Click!");
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}


