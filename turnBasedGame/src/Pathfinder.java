import java.util.*;

public class Pathfinder {
    static class Node{
        int x, y, g, h, f;
        Node parentNode;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    
    //start x,y would be the enemy's pos while goal x,y is where the player is
    public static List<Node> findPath(Map map, int startX,int startY, int goalX, int goalY){
        int mapSize = map.getMapSize();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n->n.f));

        boolean[][] closedSet = new boolean[mapSize][mapSize];
        Node[][] node = new Node[mapSize][mapSize];

        Node start = new Node(startX, startY);
        start.g = 0;
        start.h = 0; //placeholder
        start.f = start.g + start.h();

        openSet.add(start);
        node[startX][startY] = start;

        while(!openSet.isEmpty()){
            //checking the lowest f(n)
            Node current = openSet.poll();

            if(current.x == goalX && current.y == goalY){
                return; //placeholder
            }

            //exploring 4 different neighboring directions or already visited
            for(int[] direction : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}){
                int nx = current.x + direction[0];
                int ny = current.y + direction[1];

                //map bounds, blocked paths
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize){
                    continue;
                }else if(map.getTile(nx, ny) == Map.Tile.M || map.getTile(nx, ny) == Map.Tile.S){
                    continue;
                }else if(closedSet[nx][ny]){
                    continue;
                }

                //will continue from here
            }
        } 
    }
}
