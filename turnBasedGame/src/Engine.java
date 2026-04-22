import java.util.Scanner;
import Enemy.Enemy;
import Enemy.Pathfinder;
import Items.Gear;
import Map.Map;
import Player.Player;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
    private Map map;
    private Player player;
    private Scanner scanner;
    private List<Enemy> enemies = new ArrayList<>();
    private Combat combat;
    private String gamePopUp = "";
    private int islLevel = 1;
    private boolean fromDock = false;
    private List<Map> islandList = new ArrayList<>();
    private Random random = new Random();
    private int enemyRespawnTimer = 0;
    private static final int respawnTurns = 3;

    public Engine(){
       Map firstMap = new Map(10);
       System.out.println("Generating map...");
       firstMap.generateMap();
       firstMap.spawnDepartingDock();
       System.out.println("Map Generated!");
       islandList.add(firstMap);
       map = firstMap;
       player = new Player();
       System.out.println("Spawning the Player and Enemies");
       int[] playerSpawnPoint = map.spawnPoint();
       player.setPosition(playerSpawnPoint[0], playerSpawnPoint[1]);
       spawnEnemies(islLevel);
       System.out.println("Player and enemies have been spawned");
       scanner = new Scanner(System.in);
       combat = new Combat();
    }

    boolean inGame = true;

    private boolean isTileOccupied(int x, int y){
        if(player.getX() == x && player.getY() == y){
            return true;
        }
        for(Enemy enemy : enemies){
            if(enemy.getX() == x && enemy.getY() == y){
                return true;
            }
        }
        return false;
    }

    private void spawnEnemies(int islLevel){
            enemies.clear();
            int count = islLevel <= 2 ? 1 : islLevel - 1;
            for(int i = 0; i < count; i++){
                Enemy enemy = new Enemy(islLevel);
                int[] enemySpawnPoint;
                do{
                    enemySpawnPoint = map.spawnPoint();
                }while(isTileOccupied(enemySpawnPoint[0], enemySpawnPoint[1]));
                enemy.setPosition(enemySpawnPoint[0], enemySpawnPoint[1]);
                enemies.add(enemy);
            }
    }
    private Enemy getEnemyAtPos(int x, int y){
        for(Enemy enemy : enemies){
            if(enemy.getX() == x && enemy.getY() == y){
                return enemy;
            }
        }
        return null;
    }

    public void gameStart(){
        System.out.println("\n=====Island " + islLevel + "=====");
        map.mapPrint(player, enemies);
        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth() + " | " + "Mana: " + player.getMana() + "/" + player.getMaxMana());
        System.out.println("Use WASD to move. I for Inventory. Q to return to menu.");
        while(inGame && player.isPlayerAlive()){
            playerTurn();
            fromDock = false;
            Enemy encounteredEnemy = getEnemyAtPos(player.getX(), player.getY());
            if(encounteredEnemy != null){
                combat.combatMode(player, encounteredEnemy);
                if(!encounteredEnemy.isEnemyAlive()){
                    player.expGain(encounteredEnemy.gainXPReward());
                    enemies.remove(encounteredEnemy);
                    enemyRespawnTimer = respawnTurns;
                }
            }else{
                enemyTurn();
                Enemy hunter = getEnemyAtPos(player.getX(), player.getY());
                if(hunter != null){
                    combat.combatMode(player, encounteredEnemy);
                    if(!hunter.isEnemyAlive()){
                        player.expGain(hunter.gainXPReward());
                        enemies.remove(hunter);
                        enemyRespawnTimer = respawnTurns;
                    }
                }
            }

            if(enemies.isEmpty()){
                if(enemyRespawnTimer > 0){
                    enemyRespawnTimer--;
                    System.out.println("An enemy will appear in " + enemyRespawnTimer + " turns");
                }else{
                    System.out.println("An enemy has appeared!");
                    Enemy newEnemy = new Enemy(islLevel);
                    int[] enemySpawnPoint;
                    do{
                        enemySpawnPoint = map.spawnPoint();
                    }while(isTileOccupied(enemySpawnPoint[0], enemySpawnPoint[1]));
                    newEnemy.setPosition(enemySpawnPoint[0], enemySpawnPoint[1]);
                    enemies.add(newEnemy);
                }
            }
            if(map.nextToDock(player, Map.Tile.O)){
                gamePopUp = "Press F to go to the next island with the dock";
            }else if(map.nextToDock(player, Map.Tile.V)){
                gamePopUp = "Press F to go to return back to the island with the dock";
            }
            System.out.println();
            map.mapPrint(player, enemies);
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println(gamePopUp);
            System.out.println("Use WASD to move. I for Inventory. Q to return to menu.");
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
            player.setPosition(newSpawn[0], newSpawn[1]);
        }
        spawnEnemies(islLevel);
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
                break;
                case "I":
                gamePopUp = "------Inventory------";
                InventoryMenu inventoryMenu = new InventoryMenu(player, false);
                inventoryMenu.display();
                break;
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
            Gear climbingGear = player.getGearByType(true);
            if(climbingGear != null){
                climbingGear.useGear();
                System.out.println("Used climbing gear! [" + climbingGear.getUses() + " uses left]");

                if(climbingGear.isGearBroken()){
                    player.getInvItems().remove(climbingGear);
                    System.out.println("Your climbing gear has broken!");
                }
                player.movement(dx, dy, map.getMapSize());
            }else{
                System.out.println("Mountains are blocking your path!");
            }
            return;
        }else if(tile == Map.Tile.S){
            System.out.println("The Sea are blocking your path!");
            return;
        }else if(tile == Map.Tile.O){
            return;
        }
        player.movement(dx, dy, map.getMapSize());
    }

    public void enemyTurn(){
        for(Enemy enemy : enemies){
            if(enemy.playerInSight(player)){
                List<Pathfinder.Node> path = new Pathfinder().findPath(map, enemy.getX(), enemy.getY(), player.getX(), player.getY());
                if(path != null && path.size() > 1){
                    Pathfinder.Node next = path.get(1);
                    enemy.movement(next.getX() - enemy.getX(), next.getY() - enemy.getY(), map);
                }
            }else{
                int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
                int[] dir = directions[random.nextInt(4)];
                int nx = enemy.getX() + dir[0];
                int ny = enemy.getY() + dir[1];
                if(nx >= 0 && nx < map.getMapSize() && ny >= 0 && ny < map.getMapSize()){
                    if(map.getTile(nx, ny) != Map.Tile.M && map.getTile(nx, ny) != Map.Tile.S){
                        enemy.movement(dir[0], dir[1], map);
                    }
                }
            }
        }
    }


}
