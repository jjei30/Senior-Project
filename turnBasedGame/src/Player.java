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
    public void movement(int dx, int dy){
        int moveX = x +dx;
        int moveY = y +dy;

        x = moveX;
        y = moveY;
    }

    public void moveUp(){
        movement(-1, 0);
    }

    public void moveDown(){
        movement(1,0);
    }

    public void moveLeft(){
        movement(0, -1);
    }
    public void moveRight(){
        movement(0, 1);
    }

    public boolean isPlayerAlive(){
        if(health > 0){
            return true;
        }else{
            return false;
        }
    }

}
