import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Enemy.Enemy;
import Player.Player;
import Spells.Spells;

public class Combat {
    private Scanner scanner = new Scanner(System.in);
    String action;
    boolean escaped = false;
    private int enemyAggressiveness = 0;
    private int enemySuccess = 0;
    private int enemyFail = 0;

    public void combatMode(Player player, Enemy enemy){
        while(player.isPlayerAlive() && enemy.isEnemyAlive() && escaped == false){
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
        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("---------------------");
    }

    private void playerTurn(Player player, Enemy enemy){
        System.out.println("[1. Physical Attack] [2. Spell] [3. Item] [4. Escape]");
        System.out.println("---------------------");
        System.out.print("Choose your action: ");
        String input = scanner.nextLine();

        switch(input){
            case "1":
                action = "Attacking...";
                enemy.takeDamage(15);
                pause(1000);
                break;
            case "2":
               action = "What Spell are you choosing?";
               List<Spells> spells = player.getSpells();
                for(int i = 0; i < spells.size(); i++){
                    System.out.println((i+1) + ". " + spells.get(i).getSpellName() + " Mana Cost: " + spells.get(i).getManaCost());
                }
                int spellChoice = scanner.nextInt();
                Spells selectedSpell = spells.get(spellChoice-1);
                selectedSpell.playerSpellCast(player, enemy, selectedSpell.getSpellName());
                pause(1000);
                break;
            case "3":
                action = "Using an item..."; //will make items soon
                pause(1000);
                break;
            case "4":
                 action = "Attempting to escape...";
                int oneDTwenty = (int)(Math.random()*21);
                System.out.println("Rolled: " + oneDTwenty);
                double playerHealthRatio = (double)(player.getHealth()/player.getMaxHealth());
                System.out.println("Player's current Health Ratio: " + playerHealthRatio);
                double enemyHealthRatio = (double)(enemy.getHealth()/enemy.getMaxHealth());
                System.out.println("Enemy's current Health Ratio: " + enemyHealthRatio);

                int playerChances = oneDTwenty + player.getDexterity() + (int)(playerHealthRatio*10);
                int enemyResistance = (int)(enemyHealthRatio*10);
                System.out.println("Player's chances: " + playerChances + " vs Enemy's chances: " + enemyResistance);

                if(playerChances >= enemyResistance){
                    action = "Escape is successful";
                    escaped = true;
                }else{
                    action = "Escape failed..";
                }
               
                break;
            default:
                System.out.println("Invalid move!");
        }
    }
    

    private void enemyTurn(Player player, Enemy enemy){
        System.out.println("Enemy is thinking");
        pause(1000);
       
    }
     //just to add some delay for gaming experience purposes
    private void pause(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
