package Enemy.EnemyBehaviorTree;
import Enemy.Enemy;

public class LowHPCond extends Nodes {
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
