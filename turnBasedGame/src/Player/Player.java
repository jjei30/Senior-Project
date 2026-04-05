package Player;
import java.lang.Math;
import java.util.Scanner;

public class Player {
    private int x;
    private int y;
    private int health;
    private int maxHealth = 100;
    private int mana;
    private int maxMana = 30;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int lvl = 1;
    private int exp = 0;
    private int expUntilNextLevel = 100;



    //for starting position
    public Player(int startingX, int startingY){
        this.x = startingX;
        this.y = startingY;
        this.health = maxHealth;
        this.mana = maxMana;
        this.strength = 0;
        this.dexterity = 0;
        this.intelligence = 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHealth(){
        return health;
    }

    public int maxHealth(){
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
    public int getDexterity(){
        return dexterity;
    }
    public int getIntelligence(){
        return intelligence;
    }

    //player movement methods
    public void movement(int dx, int dy, int mapSize){
        int moveX = x +dx;
        int moveY = y +dy;
        if(moveX >= 0 && moveX < mapSize && moveY >= 0 && moveY < mapSize){
            x = moveX;
            y = moveY;
        }
        
    }

    public boolean isPlayerAlive(){
        if(health > 0){
            return true;
        }else{
            return false;
        }
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health < 0){
            health = 0;
        }

        System.out.println("You took " + damage + " damage!");
    }

    //healing function will go here as it will depend if the player has the potion

    public void manaDrain(int drain){
        mana -= drain;
    }

    public void lvlUp(){
        Scanner scanner = new Scanner(System.in);
        lvl++;
        expUntilNextLevel = 100*(int)(Math.pow(2,lvl-1)); 
        maxHealth += 10;
        health += 10;
        maxMana += 5;
        mana += 5;
        boolean isConfirmed = false;
        while(isConfirmed){
            System.out.println("===============================\n" 
                            + "LEVEL "+ lvl + " REACHED!\n"
                            + "===============================\n"
                            + "What would you like to upgrade?\n"
                            + "[1. STRENGTH] [2. DEXTERITY] [3. INTELLIGENCE]\n"
                            + "===============================\n");
            String input = scanner.nextLine();
            int newValueUpgrade;

            switch(input){
                case "1":
                    System.out.println("Are you sure you want to upgrade strength?\n"
                                        + "[Yes]" + "[No]");
                    String secondInput = scanner.nextLine().toUpperCase();
                    if(secondInput == "YES"){
                        newValueUpgrade = strength+1;
                        System.out.println("Strength has been upgraded from " + strength + " to " + newValueUpgrade);
                        strength = newValueUpgrade;
                        isConfirmed = true;
                        break;
                    }else if(secondInput == "NO"){
                        break;
                    }
            }
        }
        
    }

    public void expGain(int amount){
        exp += amount;
        System.out.println("======================");
        System.out.println("GAINED: " + amount + " XP");
        System.out.println("======================");
        //in case in the rare circumstance a player gets two or more levels up
        while(exp >= expUntilNextLevel){
            lvlUp();
        }
    }
}
