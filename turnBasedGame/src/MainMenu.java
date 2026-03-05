import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu(){
        scanner = new Scanner(System.in);
    }

    public void display(){
        boolean active = true;

        while(active){
            System.out.println("=== TURN BASED GAME ===");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Quit");

            System.out.print("Please Select an Option: ");
            
            String input = scanner.nextLine();
            
            switch(input){
                case "1":
                    System.out.println("New Game"); //placeholder
                break;
                case "2":
                    System.out.println("Loading game..."); //placeholder
                break;
                case "3":
                    System.out.println("Quitting the game..."); 
                    active = false;
                break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    
}
