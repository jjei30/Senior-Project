import java.util.Scanner;
import java.util.List;

import Enemy.Enemy;
import Enemy.EnemyBehaviorTree.MainBehaviorTree;
import Player.Player;
import Spells.Spells;

public class Combat {
    private Scanner scanner = new Scanner(System.in);
    String action;
    boolean escaped = false;

    public void combatMode(Player player, Enemy enemy){
        while(player.isPlayerAlive() && enemy.isEnemyAlive() && escaped == false){
            player.effectApply();
            enemy.effectApply();
            combatUI(player, enemy);
            playerTurn(player, enemy);
            player.weaponTurnTime();
            enemyTurn(player, enemy);
        }
    }

    private void combatUI(Player player, Enemy enemy){
        System.out.println("\n====BATTLE====");
        System.out.println("Enemy Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println("Enemy Mana: " + enemy.getMana()); //placeholder
        System.out.println("---------------------");
        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("---------------------");
    }

    private void playerTurn(Player player, Enemy enemy){
        if(player.isFrozenStatus()){
            System.out.println("You are frozen and cannot act!");
            return;
        }
        System.out.println("[1. Physical Attack] [2. Spell] [3. Item] [4. Escape]");
        System.out.println("---------------------");
        System.out.print("Choose your action: ");
        String input = scanner.nextLine();

        switch(input){
            case "1":
                action = "Attacking...";
                player.attack(enemy);
                pause(1000);
                break;
            case "2":
               action = "What Spell are you choosing?";
               List<Spells> spells = player.getSpells();
                for(int i = 0; i < spells.size(); i++){
                    System.out.println((i+1) + ". " + spells.get(i).getSpellName() + " Mana Cost: " + spells.get(i).getManaCost());
                }
                String spellInput = scanner.nextLine();
                int spellChoice = Integer.parseInt(spellInput) - 1;
                if(spellChoice < 0 || spellChoice >= spells.size()){
                    System.out.println("Invalid spell choice!");
                    break;
                }else{
                    Spells selectedSpell = spells.get(spellChoice);
                    selectedSpell.playerSpellCast(player, enemy);
                }
                pause(1000);
                break;
            case "3":
                InventoryMenu inventoryMenu = new InventoryMenu(player, true);
                inventoryMenu.display();
                player.weaponTurnTime();
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
        if(enemy.getEffects().stream().anyMatch(e -> e.isFrozen())){
            System.out.println("Enemy is frozen and cannot act!");
            return;
    }
        MainBehaviorTree enemyBehaviorTree = new MainBehaviorTree(enemy, player, null);
        enemyBehaviorTree.tick();
        player.effectApply();
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
