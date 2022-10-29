package mainpack;

import arena.dvdArena;
import view.View;

import java.util.Scanner;

public class mainC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        dvdArena arena = new dvdArena();
        arena.StartFight();
        View.Record();
        View.printPreviousFight();
        View.close();
    }
}
