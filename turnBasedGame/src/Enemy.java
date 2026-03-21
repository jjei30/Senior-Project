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
}
