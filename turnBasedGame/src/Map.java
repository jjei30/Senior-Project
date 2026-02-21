import java.util.*;

public class Map{
    //X is grass, S is water, M is mountain, Y is tree
    enum Tile{
        X, S, M, Y
    }

    
    private int size; 
    private Set<Tile>[][] mapGrid;
    private Random random = new Random();

    public Map(int size){
        this.size = size;
        mapGrid = new HashSet[size][size];

        //initializing all possible tiles
        for(int x=0; x < size; x++){
            for(int y=0; y < size; y++){
                mapGrid[x][y] = new HashSet<>(Arrays.asList(Tile.values()));
            }
        }
        
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
            int y = cell[0];

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
            if(withinBounds(x, y) && mapGrid[nx][ny].size() > 1){
                mapGrid[nx][ny].retainAll(Neighbours(tile));
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
            //mountain neighbours grass and mountains
            case M:
                return Set.of(Tile.X, Tile.M);
            //trees do not neighbour the sea
            case Y:
                return Set.of(Tile.X, Tile.M, Tile.Y);
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

        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
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

    
}