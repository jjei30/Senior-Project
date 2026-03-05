public class Player {
    private int x;
    private int y;
    private int health;
    private int maxHealth = 100;

    //for starting position
    public Player(int startingX, int startingY){
        this.x = startingX;
        this.y = startingY;
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

    public int maxHealth(){
        return maxHealth;
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

}
