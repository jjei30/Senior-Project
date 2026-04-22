import Player.Player;
import Items.*;
import java.util.List;
import java.util.Scanner;

public class InventoryMenu {
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private boolean isInCombat;

    public InventoryMenu(Player player, boolean isInCombat){
        this.player = player;
        this.isInCombat = isInCombat;
    }

    public void display(){
        List<Item> items = player.getInvItems();

        if(items.isEmpty()){
            System.out.println("Your inventory is empty!");
            return;
        }

        System.out.println("\n====INVENTORY====");
        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            String extra = "";
            if(item instanceof Gear){
                extra = " [Uses: " + ((Gear)item).getUses() + "]";
            }else if(item instanceof Weapon){
                extra = " [DMG: +" + ((Weapon)item).getDmg() + "]";
            }else if(item instanceof Consumable){
                Consumable c = (Consumable) item;
                if(c.getHealthRecover() > 0) extra = " [Restores " + c.getHealthRecover() + " HP]";
                else if(c.getManaRecover() > 0) extra = " [Restores " + c.getManaRecover() + " Mana]";
            }
            System.out.println((i+1) + ". " + item.getName() + extra);
        }
        System.out.println("0. Close inventory");
        System.out.print("Choose an item: ");

        String input = scanner.nextLine();
        int choice;
        try{
            choice = Integer.parseInt(input) - 1;
        }catch(NumberFormatException e){
            System.out.println("Invalid input!");
            return;
        }

        if(choice == -1) return; 

        if(choice < 0 || choice >= items.size()){
            System.out.println("Invalid choice!");
            return;
        }

        useItem(items.get(choice));
    }

    private void useItem(Item item){
        if(item instanceof Consumable){
            Consumable consumable = (Consumable) item;
            if(consumable.getHealthRecover() > 0){
                player.heal(consumable.getHealthRecover());
                System.out.println("Used " + item.getName() + "! Restored " + consumable.getHealthRecover() + " HP.");
            }else if(consumable.getManaRecover() > 0){
                player.manaRestore(consumable.getManaRecover());
                System.out.println("Used " + item.getName() + "! Restored " + consumable.getManaRecover() + " Mana.");
            }
            player.getInvItems().remove(item);
        }else if(item instanceof Weapon){
            if(isInCombat){
                player.equipWeap((Weapon) item);
            }else{
                System.out.println("You can only equip weapons during combat!");
            }
        }else if(item instanceof Gear){
            System.out.println("Gear is used automatically when traversing terrain.");
        }
    }
}
