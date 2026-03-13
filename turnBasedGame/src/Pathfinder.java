import java.util.*;

public class Pathfinder {
    static class Node{
        int x, y;
        int f;
        int g;
        int h;
        Node parentNode;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        void AStarFormula(){
            f = g + h;
        }
    }
    //start x,y would be the enemy's pos while goal x,y is where the player is
    public static List<Node> findPath(Map map, int startX,int startY, int goalX, int goalY){

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n->n.f));

        List<Node> closedSet = new ArrayList<>();

        Node start = new Node(startX, startY);

        openSet.add(start);

        while(!openSet.isEmpty()){
            //checking the lowest f(n)
            Node current = openSet.poll();

            if(current.x == goalX && current.y == goalY){
                return; //placeholder
            }
        }
    }
}
