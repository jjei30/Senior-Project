package Map;
import java.util.*;

import Enemy.Enemy;
import Player.Player;

public class Map{
    //X is grass, S is water, M is mountain, Y is tree, O is for departing dock and V for returning Dock, H is chest, and L is library
    public enum Tile{
        X, S, M, Y, O, V, H, L
    }

    
    private int size; 
    private Set<Tile>[][] mapGrid;
    private Random random = new Random();

    public Map(int size){
        this.size = size;
        mapGrid = new HashSet[size][size];

        //initializing all possible tiles (except for sea which surrounds the coast and making sure that it is surrounded by the sea, also reducing the amount of mountains near the coast
        for(int x=0; x < size; x++){
            for(int y=0; y < size; y++){
                if(x == 0 || y == 0 || x == size-1 || y == size-1){
                    mapGrid[x][y] = new HashSet<>(Set.of(Tile.S));
                }else{
                    mapGrid[x][y] = new HashSet<>(Arrays.asList(Tile.X, Tile.M, Tile.Y, Tile.H, Tile.L));

                    if(x == 1 || y == 1 || x == size-2 || y==size-2){
                        mapGrid[x][y].remove(Tile.M);
                    }
                }
                
            }
        }
        
    }
    

    public int[] spawnReturnDock(){
        System.out.println("Placing docks...");
        for(int x = size -1; x >= 0; x--){
            for(int y = size-1; y>=0; y--){
                if(getTile(x,y) == Tile.S){
                    if(getTile(x,y) == Tile.O || getTile(x,y) == Tile.V){
                        continue;
                    }
                    for(int[] m : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
                        int nx = x + m[0];
                        int ny = y + m[1];
                        
                        if(withinBounds(nx, ny)){
                            Tile neighbor = getTile(nx, ny);
                        
                            if(neighbor == Tile.X || neighbor == Tile.Y){
                                mapGrid[x][y].clear();
                                mapGrid[x][y].add(Tile.V);
                                return new int[]{nx, ny};
                            }
                        }
                    }
                }
            System.out.println("Docks have been placed at " + x + ", " + y + "!");
            }
        }
        throw new IllegalStateException("No valid area to place the dock");
    }

    public void spawnDepartingDock(){
        System.out.println("Placing docks");
        for(int x = 0; x < size; x++){
            for(int y = 0; y<size; y++){
                if(getTile(x,y) == Tile.S){
                    for(int[] m : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
                        int nx = x + m[0];
                        int ny = y + m[1];
                        
                        if(withinBounds(nx, ny)){
                            Tile neighbor = getTile(nx, ny);
                        
                            if(neighbor == Tile.X || neighbor == Tile.Y){
                                mapGrid[x][y].clear();
                                mapGrid[x][y].add(Tile.O);
                                return;
                            }
                        }
                    }
                }
            }
            
        }
        System.out.println("Docks have been placed!");
    }
    
    
    public boolean nextToDock(Player player, Tile dockTile){
        for(int[]m: new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
            int nx = player.getX() + m[0];
            int ny = player.getY() + m[1];

            if(getTile(nx, ny) == dockTile){
                return true;
            }
        }
        return false;
    }
    

    //check for every cell if it has been reduced to a tile
    private boolean isCollapsed(){
        for(int x=0; x < size; x++){
            for(int y=0; y < size; y++){
                if(mapGrid[x][y].size() > 1){
                    return false;
                }
            }
        }
        return true;
    }

    
//used to generate the map
    public void generateMap(){
        //until all cells have one tile
        while(!isCollapsed()){
            int[] cell = findLowestEntropyCell();
            int x = cell[0];
            int y = cell[1];

            //choose tile with the possibilities
            Tile tile = pickRandom(mapGrid[x][y]);
            //collapsing that cell into a single tile
            mapGrid[x][y].clear();
            mapGrid[x][y].add(tile);

            //propagating constraints to neighbour cells
            Propagate(x, y);
        }
    }
    //taking a random element using a set of tiles
    private Tile pickRandom(Set<Tile> set){
        int n = random.nextInt(set.size());
        int i = 0;

        for(Tile tile : set){
            if(i == n){
                return tile;
            }
            i++;
            
        }
        return null;
        
    }
    //Constraint propagation: placing a tile adds constraints to nearby areas, updating all adjacent locations and in turn updates their neighbours
    private void Propagate(int x, int y){
        Tile tile = mapGrid[x][y].iterator().next();
        

        for(int[] direction : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
            int nx = x + direction[0];
            int ny = y + direction[1];
            //only update the neighbours inside the grid and not the ones that have already been collapsed
            if(withinBounds(nx, ny) && mapGrid[nx][ny].size() > 1){
                Set<Tile> allowedNeighTiles = Neighbours(tile);
                mapGrid[nx][ny].retainAll(allowedNeighTiles);

                if(mapGrid[nx][ny].isEmpty()){
                    mapGrid[nx][ny].addAll(Arrays.asList(Tile.X, Tile.M, Tile.Y));
                }
            }
        }
    }

    //checking if the coordinates are within bounds
    private boolean withinBounds(int x, int y){
        if(x >=0 && x < size && y >= 0 && y < size){
            return true;
        }else{
            return false;
        }
    }
    //tiles that can be neighbours with other tiles
    private Set<Tile> Neighbours(Tile tile){
        switch(tile){
            //grass neighbours anything
            case X:
                return Set.of(Tile.X, Tile.M, Tile.Y, Tile.S);
            //mountain neighbours grass, trees, and mountains
            case M:
                return Set.of(Tile.X, Tile.M, Tile.Y);
            //trees do not neighbour the sea
            case Y:
                return Set.of(Tile.X, Tile.Y, Tile.M);
            //Sea neighbours grass and water
            case S:
                return Set.of(Tile.X, Tile.S);
            default:
                return Set.of(Tile.X, Tile.M, Tile.Y, Tile.S);
        }
    }
    //searching for the lowest entropy cell aka smallest possible number of tiles
    private int[] findLowestEntropyCell(){
        int max = Integer.MAX_VALUE;
        int[] pos = new int[2];

        for(int x=1; x<size-1; x++){
            for(int y=1; y<size-1; y++){
                int entropy = mapGrid[x][y].size();

                //ignoring already collapsed cells just to be sure
                if(entropy > 1 && entropy < max){
                    max = entropy;
                    pos[0] = x;
                    pos[1] = y;
                }
            }
        }

        return pos;
    }

    public int[] spawnPoint(){
        while(true){
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            if(getTile(x,y) == Tile.X || getTile(x,y) == Tile.Y && walkableNeighbor(x, y)){
                return new int[]{x,y};
            }
        }
    }

    public void mapPrint(Player player, Enemy enemy){
        for(int x = 0; x < size; x++){
            for(int y=0; y < size; y++){

                if(player.getX() == x && player.getY() == y){
                    System.out.print("@" + " "); //player is displayed as @
                }else if(enemy.getX() == x && enemy.getY() == y){
                    System.out.print("#" + " "); //enemy is displayed as #
                }
                else{
                    System.out.print(mapGrid[x][y].iterator().next() + " ");
                }
            }
        System.out.println();
        }
        
    }

    public int getMapSize(){
        return size;
    }

    public Tile getTile(int x, int y){
        return mapGrid[x][y].iterator().next();
    }
    
    public boolean walkableNeighbor(int x, int y){
        for(int[] m : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
            int nx = x + m[0];
            int ny = y + m[1];

            if(withinBounds(nx, ny) && getTile(nx,ny) == Tile.X || withinBounds(nx, ny) && getTile(nx,ny) == Tile.Y){
                return true;
            }
        }
        return false;
    }
}