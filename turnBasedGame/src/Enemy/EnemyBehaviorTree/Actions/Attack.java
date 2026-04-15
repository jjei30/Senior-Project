package Enemy.EnemyBehaviorTree.Actions;
import Enemy.Enemy;
import Player.Player;

import Enemy.EnemyBehaviorTree.Nodes;
import Enemy.EnemyBehaviorTree.Stat;

public class Attack extends Nodes {
    private Enemy enemy;
    private Player player;

    public Attack(Enemy enemy, Player player){
        this.enemy = enemy;
        this.player = player;
    }

    @Override
    public Stat nodeExecute(){
        enemy.attack(player);
        return Stat.SUCCESS;
    }
}
