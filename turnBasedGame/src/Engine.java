import java.util.Scanner;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;

    public Engine(){
       map = new Map(10);
       map.generateMap();
       player = new Player(0,0);
       scanner = new Scanner(System.in);
    }

    public void gameStart(){
        boolean inGame = true;

        while(inGame && player.getHealth() > 0){

            map.mapPrint();
            System.out.println("Health: " + player.getHealth());
            System.out.println("Use WASD to move. Q to return to menu.");
        }
    }
}
