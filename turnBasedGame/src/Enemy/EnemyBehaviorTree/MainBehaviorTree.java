package Enemy.EnemyBehaviorTree;
import Enemy.Enemy;
import Player.Player;
import Map.Map;
import Spells.Spells;
import Items.*;
import Effects.Effects;
import java.util.List;
import java.util.Arrays;  
import Enemy.EnemyBehaviorTree.Actions.Attack;
import Enemy.EnemyBehaviorTree.Actions.Heal;
import Enemy.Pathfinder;


public class MainBehaviorTree {
    private Nodes node;
    private Enemy enemy;
    private Player player;
    private Map map;

    public MainBehaviorTree(Enemy enemy, Player player, Map map){
        this.enemy = enemy;
        this.player = player;
        this.map = map;
        node = buildBehaviorTree();
    }

    public void tick(){
        node.nodeExecute();
    }

    private boolean isPlayerAdjacent(){
        int dx = Math.abs(enemy.getX() - player.getX());
        int dy = Math.abs(enemy.getY() - player.getY());
        if(dx + dy == 1){
            return true;
        }else{
            return false;
        }
    }

    private Nodes buildBehaviorTree(){
        //conditions
        Nodes isPlayerAdjacent = () ->isPlayerAdjacent() ? Stat.SUCCESS : Stat.FAILURE;
        Nodes isHealthLow = () -> (enemy.getHealth() / (double) enemy.getMaxHealth() < 0.4) ? Stat.SUCCESS : Stat.FAILURE;
        Nodes hasMana = () -> (enemy.getMana() <= enemy.getMaxMana() && enemy.getMana() > 0) ? Stat.SUCCESS : Stat.FAILURE;
        Nodes hasHealthPotion = () -> {
            for(Item item : enemy.getInvItems()){
                if(item instanceof Consumable && ((Consumable)item).getHealthRecover() > 0){
                    return Stat.SUCCESS;
                }
            }
            return Stat.FAILURE;
        };

        //actions
        Nodes attackAct = new Attack(enemy, player);
        Nodes healthPotionAct = new Heal(enemy);

        Nodes offensiveCasting = () -> {
            List<Spells> spells = enemy.getSpells();
            for(Spells spell : spells){
                if(spell.getEffectType() != Effects.EffectType.HEAL && enemy.getMana() >= spell.getManaCost()){
                    spell.enemySpellCast(player, enemy);
                    return Stat.SUCCESS;
                }
            }
            return Stat.FAILURE;
        };

        Nodes chasingPlayer = () -> {
            if(map == null) return Stat.FAILURE;
            List<Pathfinder.Node> path = new Pathfinder().findPath(map, enemy.getX(), enemy.getY(), player.getX(), player.getY());
            if(path != null && path.size() > 1){
                Pathfinder.Node nextPath = path.get(1);
                enemy.movement(nextPath.getX() - enemy.getX(), nextPath.getY() - enemy.getY(), map);
                return Stat.SUCCESS;
            }
            return Stat.FAILURE;
        };

        return new FallbackNodes(Arrays.asList(
            new SequenceNodes(Arrays.asList(isPlayerAdjacent, attackAct)),
            new SequenceNodes(Arrays.asList(isHealthLow, hasHealthPotion, healthPotionAct)),
            new SequenceNodes(Arrays.asList(hasMana, offensiveCasting)),
            chasingPlayer
        ));
        
    }
    
}
