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

        while(inGame && player.isPlayerAlive()){

            map.mapPrint(player);
            System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth());
            System.out.println("Use WASD to move. Q to return to menu.");

            String input = scanner.nextLine().toUpperCase();

            switch(input){
                case "W":
                    System.out.println("Moving up");
                    break;
                case "A":
                    System.out.println("Moving left");
                    break;
                case "S":
                    System.out.println("Moving down");
                    break;
                case "D":
                    System.out.println("Moving right");
                    break;
                case "Q":
                    System.out.println("Returning to Main Menu...");
                    inGame = false;
            }
            System.out.println();
        }
    }
}
