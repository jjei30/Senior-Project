import java.util.Scanner;
import Enemy.Enemy;
import Enemy.Pathfinder;
import Map.Map;
import Player.Player;
import java.util.List;
import java.util.ArrayList;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;
    private Enemy enemy;
    private Combat combat;
    private String gamePopUp;
    private int islLevel = 1;
    private boolean fromDock = false;
    private List<Map> islandList = new ArrayList<>();

    public Engine(){
       Map firstMap = new Map(10);
       System.out.println("Generating map...");
       firstMap.generateMap();
       firstMap.spawnDepartingDock();
       System.out.println("Map Generated!");
       islandList.add(firstMap);
       map = firstMap;
       player = new Player();
       enemy = new Enemy();
       System.out.println("Spawning the Player and Enemies");
       int[] playerSpawnPoint = map.spawnPoint();
       player.setPosition(playerSpawnPoint[0], playerSpawnPoint[1]);
       spawnEntities();
       System.out.println("Player and enemies have been spawned");
       scanner = new Scanner(System.in);
       combat = new Combat();
    }

    boolean inGame = true;

    private void spawnEntities(){
        int[] enemySpawnPoint;
        do{
            enemySpawnPoint = map.spawnPoint();
        }while(enemySpawnPoint[0] == player.getX() && enemySpawnPoint[1] == player.getY());

        enemy.setPosition(enemySpawnPoint[0], enemySpawnPoint[1]);
    }

    public void gameStart(){
        System.out.println("\n=====Island " + islLevel + "=====");
        map.mapPrint(player, enemy);
        System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth() + " | " + "Mana: " + player.getMana() + "/" + player.getMaxMana());
        System.out.println("Use WASD to move. Q to return to menu.");
        while(inGame && player.isPlayerAlive()){
            playerTurn();
            fromDock = false;
            isCombat(player, enemy);
            if(map.nextToDock(player, Map.Tile.O)){
                gamePopUp = "Press F to go to the next island with the dock";
            }else if(map.nextToDock(player, Map.Tile.V)){
                gamePopUp = "Press F to go to return back to the island with the dock";
            }
            enemyTurn(enemy, player, map);
            isCombat(player, enemy);
            System.out.println();
            map.mapPrint(player, enemy);
            System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth());
            System.out.println(gamePopUp);
            System.out.println("Use WASD to move. Q to return to menu.");
        }
    }

    public void moveIsland(){
        islLevel++;
        System.out.println("\n=====Island " + islLevel + "=====");
        fromDock = true;

        if(islLevel-1 < islandList.size()){
            map = islandList.get(islLevel-1);
        }else{
            System.out.println("Travelling to the new Island, Island " + islLevel);

            Map nextMap = new Map(map.getMapSize());
            nextMap.generateMap();

            int[] newSpawn = nextMap.spawnReturnDock();

            nextMap.spawnDepartingDock();

            islandList.add(nextMap);

            map = nextMap;

            spawnEntities();

            player.setPosition(newSpawn[0], newSpawn[1]);
        }
            //here I will scale the enemy soon
    }
    public void returnIsland(){
        islLevel--;
        fromDock = true;

        System.out.println("Returning to Island " + islLevel + "...");

        map = islandList.get(islLevel-1);
        //spawnEntities();
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
                    gamePopUp = "------Moving up------";
                    playerMovement(player, map, -1, 0);
                    break;
                case "A":
                    gamePopUp = "------Moving left------";
                    playerMovement(player, map, 0, -1);
                    break;
                case "S":
                    gamePopUp = "------Moving down------";
                    playerMovement(player, map, 1, 0);
                    break;
                case "D":
                    gamePopUp = "------Moving right------";
                    playerMovement(player, map, 0, 1);
                    break;
                case "Q":
                    gamePopUp = "------Returning to Main Menu...------";
                    inGame = false;
                    break;
                case "F":
                    if(map.nextToDock(player, Map.Tile.O) && !fromDock){
                        if(player.getLvl() >= islLevel+1){
                            moveIsland();
                        }else{
                            System.out.println("You need to be at a higher level to go to the next island");
                        }
                        break;
                    }else if(map.nextToDock(player, Map.Tile.V) && !fromDock){
                        returnIsland();
                        break;
                    }
                default:
                    gamePopUp = "------That's not an option------";
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
        }else if(tile == Map.Tile.O){
            return;
        }

        player.movement(dx, dy, map.getMapSize());

    }

    public void enemyTurn(Enemy enemy, Player player, Map map){
        List<Pathfinder.Node> pathFind = new Pathfinder().findPath(map, enemy.getX(), enemy.getY(), player.getX(), player.getY());

        if(pathFind != null && pathFind.size() > 1){

            Pathfinder.Node nextNode = pathFind.get(1);

            int dx = nextNode.getX() - enemy.getX();
            int dy = nextNode.getY()- enemy.getY();

            enemy.movement(dx, dy, map);
        }

    }
}
