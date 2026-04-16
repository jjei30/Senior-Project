package Enemy.EnemyBehaviorTree.Conditions;
import Enemy.Enemy;
import Enemy.EnemyBehaviorTree.Nodes;
import Enemy.EnemyBehaviorTree.Stat;

public class LowHPCond implements Nodes {
    private Enemy enemy;

    public LowHPCond(Enemy enemy){
        this.enemy = enemy;
    }

    @Override
    public Stat nodeExecute(){
        if(enemy.getHealth() < 20){
            return Stat.SUCCESS;
        }else{
            return Stat.FAILURE;
        }
    }
}
