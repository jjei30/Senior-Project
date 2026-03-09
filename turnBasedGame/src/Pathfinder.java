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
}
