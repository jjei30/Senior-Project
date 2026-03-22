
import java.util.Scanner;

public class Combat {
    private Scanner scanner = new Scanner(System.in);

    public void combatMode(Player player, Enemy enemy){
        while(player.isPlayerAlive() && enemy.isEnemyAlive()){
            combatUI(player, enemy);
            playerTurn(player, enemy);

        }
    }

    private void combatUI(Player player, Enemy enemy){
        System.out.println("\n====BATTLE====");
        System.out.println("Enemy Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println("Enemy Mana: " + "Mana"); //placeholder
        System.out.println("---------------------");
        System.out.println("Health: " + player.getHealth() + "/" + player.maxHealth());
        System.out.println("---------------------");
    }

    private void playerTurn(Player player, Enemy enemy){
        System.out.println("[1. Physical Attack] [2. Spell] [3. Item] [4. Escape]");
        System.out.println("---------------------");
        System.out.print("Choose your action: ");
        String input = scanner.nextLine();

        switch(input){
            case "1":
                System.out.println("Attacking"); //placeholder
                break;
            case "2":
                System.out.println("Casting a spell"); //placeholder
                break;
            case "3":
                System.out.println("Using item"); //placeholder
                break;
            case "4":
                System.out.println("Attempting to escape"); //placeholder
                break;
            default:
                System.out.println("Invalid move!");
            
        }
    }
}
