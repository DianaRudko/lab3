package arena;
import droidsPack.droid;
import droidsPack.droidDD;
import droidsPack.droidHealer;
import droidsPack.droidTank;


import java.util.Scanner;

import static view.View.saveWinner;

public class dvdArena {
    private int global_droids_in_team = 3;
    private droid[] firstTeam = new droid[global_droids_in_team];
    private droid[] secondTeam = new droid[global_droids_in_team];
    public dvdArena(){
        System.out.println("For choose DD - write dd; Tank - tank; Healer - heal\n Example:\ntank\nr2d2");
        System.out.println("Enter the first team:");
        pushTeam(firstTeam);
        System.out.println("Enter the second team:");
        pushTeam(secondTeam);
    }
    public void pushTeam(droid[] team){
        for(int i =0; i < global_droids_in_team; ++i){
            String type;
            String name;
            Scanner input = new Scanner(System.in);
            type = input.nextLine();
            name = input.nextLine();
            if (type.equals("dd")) {
                team[i] = new droidDD();
                team[i].setName(name);
            }
            else if (type.equals("tank")) {
                team[i] = new droidTank();
                team[i].setName(name);
            }
            else if (type.equals("heal")) {
                team[i] = new droidHealer();
                team[i].setName(name);
            }
            else{
                System.out.println("You have enter the wrong data! Try again");
                --i;
            }
        }
    }
    public void StartFight(){
        for (int i = 0, j = 0; CheckLives(firstTeam) > 0; ++i, ++j){
            while (firstTeam[j].getHealth() <= 0 && j < global_droids_in_team) {
                ++j;
                if (j > (global_droids_in_team - 1))
                    j = 0;
            }
            firstTeam[j].make_turn(firstTeam, secondTeam);
            if (j % (global_droids_in_team - 1) == 0)
                j = 0;
            if (CheckLives(secondTeam) == 0)
                break;
            while (secondTeam[i].getHealth() == 0 && i < global_droids_in_team) {
                ++i;
                if (i > (global_droids_in_team - 1))
                    i = 0;
            }
            secondTeam[i].make_turn(secondTeam, firstTeam);
            if (i % (global_droids_in_team - 1) == 0)
                i = 0;
        }
        if (CheckLives(firstTeam) == 0) {
            System.out.println("Win second team");
            saveWinner("Win second team");
        }
        else{
            System.out.println("Win first team");
            saveWinner("Win first team");
        }
    }
    public int CheckLives(droid[] team) {
        int lives_droids = 0;
        for (int i = 0; i < global_droids_in_team; ++i) {
            if (team[i].getHealth() > 0)
                ++lives_droids;
        }
        return lives_droids;
    }
}

