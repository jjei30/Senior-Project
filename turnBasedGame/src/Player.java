public class Player {
    private int x;
    private int y;

    //for starting position
    public Player(int startingX, int startingY){
        this.x = startingX;
        this.y = startingY;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
