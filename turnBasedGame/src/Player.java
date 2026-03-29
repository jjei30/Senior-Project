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
        lvl++;
        //will continue from here
    }

    public void expGain(int amount){
        exp += amount;
        System.out.println("======================");
        System.out.println("GAINED: " + amount + " XP");
        System.out.println("======================");
        //in case in the rare circumstance a player gets two levels up
        while(exp >= expUntilNextLevel){
            lvlUp();
        }
    }
}
