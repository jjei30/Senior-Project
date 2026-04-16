package Enemy.EnemyBehaviorTree.Actions;
import Enemy.Enemy;
import Player.Player;

import Enemy.EnemyBehaviorTree.Nodes;
import Enemy.EnemyBehaviorTree.Stat;

import Items.Item;
import Items.Consumable;
import java.util.List;

public class Heal implements Nodes {
    private Enemy enemy;

    public Heal(Enemy enemy){
        this.enemy = enemy;
    }

    @Override
    public Stat nodeExecute(){
        if(enemy.getHealth() >= enemy.getMaxHealth()){
            return Stat.FAILURE;
        }
        List<Item> inv = enemy.getInvItems();
        Consumable healthPotion = null;

        for(Item item : inv){
            if(item instanceof Consumable){
                Consumable consumable = (Consumable) item;
                if(consumable.getHealthRecover() > 0){
                    healthPotion = consumable;
                    break;
                }
            }
        }

        if(healthPotion == null){
            return Stat.FAILURE;
        }

        enemy.heal(healthPotion.getHealthRecover());

        inv.remove(healthPotion);
        System.out.println("Enemy used a " + healthPotion.getName() + "!");
        return Stat.SUCCESS;
    }
}
