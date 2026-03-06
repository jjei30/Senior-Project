import java.util.Scanner;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;
    private Enemy enemy;

    public Engine(){
       map = new Map(10);
       map.generateMap();
       player = new Player(0,0);
       scanner = new Scanner(System.in);
       enemy = new Enemy(5, 5);
    }

    public void gameStart(){
        boolean inGame = true;

        while(inGame && player.isPlayerAlive()){

            map.mapPrint(player, enemy);
            System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth());
            System.out.println("Use WASD to move. Q to return to menu.");

            String input = scanner.nextLine().toUpperCase();
            
            switch(input){
                case "W":
                    playerMovement(player, map, -1, 0);
                    System.out.println("Moving up");
                    break;
                case "A":
                    playerMovement(player, map, 0, -1);
                    System.out.println("Moving left");
                    break;
                case "S":
                    playerMovement(player, map, 1, 0);
                    System.out.println("Moving down");
                    break;
                case "D":
                    playerMovement(player, map, 0, 1);
                    System.out.println("Moving right");
                    break;
                case "Q":
                    System.out.println("Returning to Main Menu...");
                    inGame = false;
                default:
                    System.out.println("That's not an option");
                    break;
            }
            System.out.println();
        }
    }

    public void playerMovement(Player player, Map map, int dx, int dy){
        int moveX = player.getX() + dx;
        int moveY = player.getY() + dy;

        if(moveX < 0 || moveX >= map.getMapSize() || moveY < 0 || moveY >= map.getMapSize()){
            System.out.println("You can't move out of bounds"); //may change it up to where when the player moves out of the map it generates a new map
            return;
        }

        Map.Tile tile = map.getTile(moveX, moveY);

        if(tile == Map.Tile.M){
            System.out.println("Mountains are blocking your path!"); //when adding items will add something that can pass through the mountains
            return;
        }else if(tile == Map.Tile.S){
            System.out.println("The Waters are blocking your path!"); //Same case as the mountains above
            return;
        }

        player.movement(dx, dy, map.getMapSize());

    }
}
