public class Player {
    private int x;
    private int y;
    private int health;

    //for starting position
    public Player(int startingX, int startingY){
        this.x = startingX;
        this.y = startingY;
        this.health = 100;
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
}
