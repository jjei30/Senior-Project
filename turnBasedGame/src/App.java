import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        String input = scanner.nextLine();
        while(active){
            Map map = new Map(10); //10x10
            map.generateMap();
            map.mapPrint();
        }
        
    }
}
