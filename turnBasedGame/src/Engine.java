import java.util.Scanner;
import java.util.List;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;
    private Enemy enemy;
    private Combat combat;
    private String action;

    public Engine(){
       map = new Map(10);
       map.generateMap();
       player = new Player(0,0);
       scanner = new Scanner(System.in);
       enemy = new Enemy(5, 5);
       combat = new Combat();
    }

    boolean inGame = true;

    public void gameStart(){
        map.mapPrint(player, enemy);
        System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth() + " | " + "Mana: " + player.getMana() + "/" + player.getMaxMana());
        System.out.println("Use WASD to move. Q to return to menu.");
        while(inGame && player.isPlayerAlive()){
            playerTurn();
            isCombat(player, enemy);
            enemyTurn(enemy, player, map);
            isCombat(player, enemy);
            System.out.println();
            map.mapPrint(player, enemy);
            System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth());
            System.out.println(action);
            System.out.println("Use WASD to move. Q to return to menu.");
        }
    }

    void isCombat(Player player, Enemy enemy){
        if(player.getX() == enemy.getX() && player.getY() == enemy.getY()){
            combat.combatMode(player, enemy);
        }
    }

    public void playerTurn(){
            String input = scanner.nextLine().toUpperCase();
            
            switch(input){
                case "W":
                    action = "------Moving up------";
                    playerMovement(player, map, -1, 0);
                    break;
                case "A":
                    action = "------Moving left------";
                    playerMovement(player, map, 0, -1);
                    break;
                case "S":
                    action = "------Moving down------";
                    playerMovement(player, map, 1, 0);
                    break;
                case "D":
                    action = "------Moving right------";
                    playerMovement(player, map, 0, 1);
                    break;
                case "Q":
                    System.out.println("------Returning to Main Menu...------");
                    inGame = false;
                    break;
                default:
                    System.out.println("------That's not an option------");
                    break;
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

    public void enemyTurn(Enemy enemy, Player player, Map map){
        List<Pathfinder.Node> pathFind = new Pathfinder().findPath(map, enemy.getX(), enemy.getY(), player.getX(), player.getY());

        if(pathFind != null && pathFind.size() > 1){

            Pathfinder.Node nextNode = pathFind.get(1);

            int dx = nextNode.x - enemy.getX();
            int dy = nextNode.y - enemy.getY();

            enemy.movement(dx, dy, map);
        }

    }
     //just to add some delay for gaming experience purposes
    private void pause(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
