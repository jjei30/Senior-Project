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

    

    public void generateMap(){
        while(!isCollapsed()){

        }
    }
}