package Enemy;
import Map.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Effects.Effects;

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
    private List<Effects> effects = new ArrayList<>();

    public Enemy(){
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

    public void heal(int amount){
        health += amount;
        if(health >= maxHealth){
            health = maxHealth;
        }
    }

    public void manaDrain(int drain){
        mana -= drain;
    }

    public void effectAdd(Effects effect){
        effects.add(effect);
    }

    public void effectApply(){
        Iterator<Effects> iterator = effects.iterator();
        while(iterator.hasNext()){
            Effects effect = iterator.next();
            effect.applyEnemyEffect(this);
            effect.durationTimer();

            if(effect.effectExpired()){
                System.out.println("Enemy's " + effect.getEffectType() + " has expired!");
                iterator.remove();
            }
        }
    }
}
