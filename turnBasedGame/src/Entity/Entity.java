package Entity;

public class Entity {
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int strength;
    private int dexterity;
    private int intelligence;

    public Entity(int health, int maxHealth, int mana, int maxMana, int strength, int dexterity, int intelligence){
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getMana(){
        return mana;
    }
    public int getMaxMana(){
        return maxMana;
    }
    public int getStrength(){
        return strength;
    }
    public int getDex(){
        return dexterity;
    }
    public int getIntelligence(){
        return intelligence;
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health < 0){
            health = 0;
        }

        System.out.println("You took " + damage + " damage!");
    }

    public void manaDrain(int drain){
        mana -= drain;
    }
    

    public boolean isEntityAlive(){
        if(health > 0){
            return true;
        }else{
            return false;
        }
    }
}
