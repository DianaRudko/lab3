package droidsPack;


import view.View;

import java.util.concurrent.ThreadLocalRandom;

public class droidDD extends droid {
    private int critical_chance;
    private double life_steal_per_percent_at_damage;
    private int turns_to_end_lifesteal;

    public droidDD(){
        super("Damage dealer",90, 90, 20, 70);
        this.critical_chance = 8;
        life_steal_per_percent_at_damage = 0.3;
        turns_to_end_lifesteal = 3;
    }
    public void lifestealing(int damage){
        int plusHp = (int) (damage * life_steal_per_percent_at_damage);
        this.setHealth(this.health + plusHp);
        --turns_to_end_lifesteal;
    }
    public boolean trigger_lifesteal(){
        double hp_for_trigger_lifesteal_per_percent = max_health * 0.2;
        if (this.health < hp_for_trigger_lifesteal_per_percent)
            return true;
        return false;
    }
    @Override
    public int getMax_health(){ return this.max_health; }
    @Override
    public void make_turn(droid[] friend, droid[] enemy){
        int hit = ThreadLocalRandom.current().nextInt(0, 100);
        int k = get_damage();
        droid temp = FindTheLowest(enemy);
        if (hit + this.chance_hit >= 100) {
            if (temp.take_damage(k, this) != 0 && trigger_lifesteal() && turns_to_end_lifesteal > 0)
                lifestealing(k);
        }
        else
            k = 0;
        View.printf(this, temp, k);
    }
    @Override
    public droid FindTheLowest(droid[] team){
        int health = 120;
        droid nom = null;
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
    public int get_damage(){
        int crit = ThreadLocalRandom.current().nextInt(0, 100);
        if (this.critical_chance + crit >= 100)
            return ThreadLocalRandom.current().nextInt(30-7, 30+7);
        return ThreadLocalRandom.current().nextInt(20-5, 20+5);
    }
    @Override
    public String get_Class(){
        return "dd";
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
        return "Damage dealer " + this.name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
}

