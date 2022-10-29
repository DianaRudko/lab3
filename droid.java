package droidsPack;

public class droid {
    protected String name;
    protected int max_health;
    protected int health;
    protected int damage;
    protected int chance_hit;
    public droid() {}
    public droid(String name, int max_health, int health, int damage, int chance_hit){
        this.name = name;
        this.max_health = max_health;
        this.health = health;
        this.damage = damage;
        this.chance_hit = chance_hit;
    }
    public int getMax_health(){ return this.max_health; }
    public String get_Class(){
        return "droid";
    }
    public int getHealth() {
        return health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void make_turn (droid[] first, droid[] second){}
    public int take_damage(int damage,droid attacker){
        this.setHealth(this.getHealth()-damage);
        return damage;
    }
    public String get_name(){
        return this.name;
    }
    public int get_damage() {
        return this.damage;
    }
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
}

