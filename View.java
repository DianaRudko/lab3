package view;

import droidsPack.droid;

import java.io.*;
import java.util.Scanner;

public class View {
    private static PrintWriter printWriter;
    private static String filePath = "C:\\Users\\Rudko\\IdeaProjects\\lab3\\out\\production\\lab3\\lab3.txt";
    private static boolean wasSomethingPrinted;
    private static boolean wasRecorded;

    static {
        File fightFile = new File(filePath);
        try {
            fightFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            printWriter = new PrintWriter(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveWinner(String buf){
        printWriter.println("\n" + buf);
    }
    public static void printf(droid attacker, droid enemy, int damage) {
        if (damage == 0) {
            System.out.printf("%s miss in %s\n", attacker.get_name(), enemy.get_name());
            printWriter.println(attacker.get_name() + " miss in " + enemy.get_name() + "\n");
        }
        else if (damage < 0) {
            System.out.printf("%s heal %s on %d health\n", attacker.get_name(), enemy.get_name(), -1 * damage);
            printWriter.println(attacker.get_name() + " heal " + enemy.get_name() + " on " + -1 * damage + " health\n");
        }
        else {
            System.out.printf("%s hit %s in %d damage\n", attacker.get_name(), enemy.get_name(), damage);
            printWriter.println(attacker.get_name() + " hit " + enemy.get_name() + " in " + damage + " damage\n");
        }
        System.out.printf("%s health = \'%d\'\n", enemy.get_name(), enemy.getHealth());
        printWriter.println(attacker.get_name() + " health = \'" + enemy.getHealth() + "\'\n");
        if (attacker != enemy) {
            System.out.printf("%s health = \'%d\'\n", attacker.get_name(), attacker.getHealth());
            printWriter.println(attacker.get_name() + " health = " + attacker.getHealth() + "\n");
        }
        System.out.println("--------------------------");
        printWriter.println("--------------------------\n");
        wasSomethingPrinted = true;
    }
    public static void Record()
    {
        if(!wasSomethingPrinted)
        {
            System.out.println("Не було проведено бою");
            return;
        }
        printWriter.flush();
        wasRecorded = true;
        System.out.println("Бій записано\n");
    }

    public static void close()
    {
        printWriter.close();
    }
    public static void printPreviousFight()  {
        if(!wasRecorded)
        {
            System.out.println("Ще не було записаного бою");
            return;
        }
        if (askView() == false)
            return;
        File file = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext())
        {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    public static boolean askView() {
        System.out.println("Do u want to see the fight? yes - no");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String yesNo = scanner.nextLine();
            if (yesNo.equals("yes"))
                return true;
            else if (yesNo.equals("no"))
                return false;
            else {
                System.out.println("U enter wrong data, try harder. yes - no");
            }
        }
    }
}
