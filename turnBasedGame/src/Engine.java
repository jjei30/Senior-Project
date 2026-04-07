import java.util.Scanner;

import Enemy.Enemy;
import Enemy.Pathfinder;
import Map.Map;
import Player.Player;

import java.util.List;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;
    private Enemy enemy;
    private Combat combat;
    private String gamePopUp;
    private int islLevel = 1;
    private boolean fromDock = false;

    public Engine(){
       map = new Map(10);
       System.out.println("Generating map...");
       map.generateMap();
       System.out.println("Map Generated!");
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
        fromDock = true;

        System.out.println("Travelling to the new island...");

        map = new Map(map.getMapSize());
        map.generateMap();

        map.spawnReturnDock();

        spawnEntities();

        player.setPosition(map.spawnPoint()[0], map.spawnPoint()[1]);

        //here I will scale the enemy soon
    }
    public void returnIsland(){
        islLevel--;
        fromDock = true;

        System.out.println("Returning to Island...");

        map = new Map(map.getMapSize());
        map.generateMap();

        spawnEntities();
        player.setPosition(map.spawnPoint()[0], map.spawnPoint()[1]);

        //here I will scale the enemy soon
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
                        moveIsland();
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

     //just to add some delay for gaming experience purposes
    private void pause(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
