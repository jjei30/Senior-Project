public class Enemy {
    
    private int x;
    private int y;
    private int health;
    private int maxHealth = 50;

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
}
