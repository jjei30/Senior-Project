
import java.util.Scanner;

public class Combat {
    private Scanner scanner = new Scanner(System.in);

    public void combatMode(Player player, Enemy enemy){
        while(player.isPlayerAlive() && enemy.isEnemyAlive()){
            combatUI(player, enemy);
            playerTurn(player, enemy);
            enemyTurn(player, enemy);
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
                System.out.println("Attacking..."); //placeholder
                enemy.takeDamage(15);
                break;
            case "2":
                System.out.println("Casting a spell"); 
                player.manaDrain(5); //will make spells list soon
                break;
            case "3":
                System.out.println("Using item"); //will create items list soon
                break;
            case "4":
                System.out.println("Attempting to escape"); //placeholder
                break;
            default:
                System.out.println("Invalid move!");
            
        }
    }
    

    private void enemyTurn(Player player, Enemy enemy){
        System.out.println("Enemy is thinking");
        thinking(1000);
        //placeholder until I implement an AI-based system
        int randomChoice = (int)(Math.random()*3);
        switch(randomChoice){
            case 0:
                System.out.println("Enemy is attacking!");
                player.takeDamage(15);
                break;
            case 1:
                System.out.println("Casting spell"); //same case as player
                enemy.manaDrain(5);
                break;
            case 2:
                System.out.println("Using item"); //same case as player
                break;
            default:
                System.out.println("Enemy is rethinking");
                thinking(1000);

        }
    }
     //just to add some delay for gaming experience purposes
    private void thinking(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
