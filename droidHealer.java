package droidsPack;


import view.View;

import java.util.concurrent.ThreadLocalRandom;

public class droidHealer extends droid {
    private int heal;

    public droidHealer(){
        super("Healer",80, 80, 15, 60);
        this.heal = 30;
    }
    public droid trigger_to_heal(droid[] team){
        int max_hp_minus_hp_now = this.heal;
        droid need_for_heal = null;
        for(int i = 0; i < team.length; ++i)
            if (team[i].getHealth() > 0 && team[i].getMax_health() - team[i].getHealth() > max_hp_minus_hp_now){
                need_for_heal = team[i];
                max_hp_minus_hp_now = team[i].getMax_health() - team[i].getHealth();
            }
        return need_for_heal;
    }
    public void heal(droid teamate){
        teamate.setHealth(teamate.getHealth() + this.heal);
    }
    @Override
    public void make_turn (droid[] friend, droid[] enemy){
        droid need_for_heal = trigger_to_heal(friend);
        int k = -this.heal;
        if (need_for_heal != null){
            heal(need_for_heal);
            View.printf(this, need_for_heal, k);
            return;
        }
        int hit = ThreadLocalRandom.current().nextInt(0, 100);
        k = get_damage();
        droid temp = FindTheLowest(enemy);
        if (hit + this.chance_hit >= 100) {
            temp.take_damage(k, this);
            View.printf(this, temp, k);
        }
        else
            k = 0;
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
        this.setHealth(this.health - damage);
        if(this.health < 0)
            this.health = 0;
        return damage;
    }
    @Override
    public int getMax_health(){ return this.max_health; }
    @Override
    public int get_damage(){
        return ThreadLocalRandom.current().nextInt(10 - 3, 10 + 3);
    }
    @Override
    public String get_Class(){
        return "heal";
    }
    @Override
    public void setHealth(int hp) {
        this.health = hp;
    }
    @Override
    public int getHealth() {
        return this.health;
    }
    @Override public String get_name(){
        return "Healer " + this.name;
    }
    @Override public void setName(String name) {
        this.name = name;
    }
}

