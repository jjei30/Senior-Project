package Enemy;
import Map.Map;
import Map.Map.Tile;

public class Enemy {
    
    private int x;
    private int y;
    private int health;
    private int maxHealth = 50;
    private int mana;
    private int maxMana = 10;
    private int strength;
    private int dexterity;
    private int intelligence;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.health = maxHealth;
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
    public int getDexterity(){
        return dexterity;
    }
    public int getIntelligence(){
        return intelligence;
    }

    public void movement(int dx, int dy, Map map){
        int moveX = x +dx;
        int moveY = y +dy;
        int mapSize = map.getMapSize();
        if(moveX >= 0 && moveX < mapSize && moveY >= 0 && moveY < mapSize){
            if(map.getTile(moveX, moveY) != Map.Tile.M || map.getTile(moveX, moveY) != Map.Tile.S){
                x = moveX;
                y = moveY;
            }
        }
        
    }

    public boolean isEnemyAlive(){
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

        System.out.println("Enemy took " + damage + " damage!");
    }

    //healing function will go here as it will depend if the enemy has the potion

    public void manaDrain(int drain){
        mana -= drain;
    }
}
