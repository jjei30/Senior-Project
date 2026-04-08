package Enemy;
import Entity.Entity;
import Map.Map;

public class Enemy extends Entity{
    
    private int x;
    private int y;
    private int maxHealth = 50;
    private int health = maxHealth;
    private int maxMana = 10;
    private int mana = maxMana;
    private int strength;
    private int dexterity;
    private int intelligence;

    public Enemy(int maxHealth, int health, int maxMana, int mana, int strength, int dexterity, int intelligence){
        super(health, maxHealth, mana, maxMana, strength, dexterity, intelligence);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
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
