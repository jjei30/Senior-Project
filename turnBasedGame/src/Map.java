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

        for(int x=0; x < size; x++){
            for(int y=0; y < size; y++){
                mapGrid[x][y] = new HashSet<>(Arrays.asList(Tile.values()));
            }
        }
        
    }
}