package Enemy;
import java.util.*;
import Map.Map;

public class Pathfinder {
    public static class Node{
        int x, y, g, h, f;
        Node parentNode; //basically to remember where it came from and how it reached the next tile

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    
    //start x,y would be the enemy's pos while goal x,y is where the player is
    public List<Node> findPath(Map map, int startX,int startY, int goalX, int goalY){
        int mapSize = map.getMapSize();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n->n.f));

        boolean[][] closedSet = new boolean[mapSize][mapSize];
        Node[][] node = new Node[mapSize][mapSize];

        //initializing the starting node
        Node start = new Node(startX, startY);
        start.g = 0;
        start.h = manhattanHeuristic(startX, startY, goalX, goalY); 
        start.f = start.g + start.h;

        openSet.add(start);
        node[startX][startY] = start;

        while(!openSet.isEmpty()){
            //checking the lowest f(n)
            Node current = openSet.poll();

            //when goal is reached
            if(current.x == goalX && current.y == goalY){
                return pathBuilder(current); //placeholder
            }

            closedSet[current.x][current.y] = true;

            //exploring 4 different neighboring directions or already visited
            for(int[] direction : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}){
                int nx = current.x + direction[0];
                int ny = current.y + direction[1];

                //skipping map bounds, blocked paths, already visited
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize){
                    continue;
                }else if(map.getTile(nx, ny) == Map.Tile.M || map.getTile(nx, ny) == Map.Tile.S){
                    continue;
                }else if(closedSet[nx][ny]){
                    continue;
                }

                int newG = current.g + 1;
                int newH = manhattanHeuristic(nx, ny, goalX, goalY);
                int newF = newG + newH;

                Node neighbor = node[nx][ny];
                
                //search for new node or even a better pathway
                if(neighbor == null || neighbor.f > newF){

                    if(neighbor == null){
                        neighbor = new Node(nx, ny);
                        node[nx][ny] = neighbor;
                    }

                    neighbor.g = newG;
                    neighbor.h = newH;
                    neighbor.f = newF;
                    neighbor.parentNode = current;

                    openSet.add(neighbor);
                }

            }
        } 
        return null; //this means that there is no path
    }

    //manhattan distance heuristic, measuring the total number of grid units an agent is away from the goal within the grid, using the formula d = |x1-x2|+|y1-y2|
    private static int manhattanHeuristic(int currentX, int currentY, int goalX, int goalY){
        int d = Math.abs(currentX - goalX) + (currentY - goalY);
        return d;
    }

    //building path from goal to start
    private static List<Node> pathBuilder(Node endNode){
        List<Node> pathNode = new ArrayList<>();
        Node currentNode = endNode;

        while(currentNode != null){
            pathNode.add(currentNode);
            currentNode = currentNode.parentNode; //go backwards to it as it goes from goal to start
        }

        Collections.reverse(pathNode); //reverse order so it knows where it should head
        return pathNode;
    }
    
}
