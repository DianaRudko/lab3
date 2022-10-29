package droidsPack;

import view.View;

import java.util.concurrent.ThreadLocalRandom;

public class droidTank extends droid {
    private int chance_block;
    private int backDamagePerProcent;

    public droidTank(){
        super("Tank",120, 120, 10, 90);
        this.chance_block = 10;
        this.backDamagePerProcent = 30;
    }

    public int def(){
        int defence = ThreadLocalRandom.current().nextInt(0, 100);
        if (defence + this.chance_block >= 100)
            return 1;
        return 0;
    }
    @Override
    public void make_turn (droid[] friend, droid[] enemy){
        int hit = ThreadLocalRandom.current().nextInt(0, 100);
        int k = get_damage();
        droid temp = FindTheLowest(enemy);
        if (hit + this.chance_hit >= 100) {
            temp.take_damage(k, this);
        }
        else
            k = 0;
        View.printf(this, temp, k);
    }
    @Override
    public droid FindTheLowest(droid[] team){
        int health = 120;
        droid nom = new droid();
        for(int i = 0; i < team.length; ++i){
            if (team[i].getHealth() <= health && team[i].getHealth() > 0){
                health = team[i].getHealth();
                nom = team[i];
            }
        }
        return nom;
    }
    @Override
    public int take_damage(int damage,droid attacker){
        if (def() == 1)
            return 0;
        this.setHealth(this.health - damage);
        attacker.setHealth((int) (attacker.getHealth()-damage*backDamagePerProcent/100.0));
        if(this.health < 0)
            this.health = 0;
        return damage;
    }
    @Override
    public int getMax_health(){ return this.max_health; }
    @Override
    public String get_Class(){
        return "tank";
    }
    @Override
    public void setHealth(int hp) {
        this.health = hp;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
    @Override
    public String get_name(){
        return "Tank " + this.name;
    }
    @Override
    public int get_damage(){
        return ThreadLocalRandom.current().nextInt(this.damage - 3, this.damage + 3);
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
}

