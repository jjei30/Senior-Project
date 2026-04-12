package Enemy.EnemyBehaviorTree;

import java.util.List;

public class SelectorNodes extends Nodes{
    private List<Nodes> children;

    public SelectorNodes(List<Nodes> children){
        this.children = children;
    }

    @Override
    public Stat nodeExecute(){
        for(Nodes c : children){
            Stat stat = c.nodeExecute();
            if(stat == Stat.SUCCESS){
                return Stat.SUCCESS;
            }
           
        }
        return Stat.FAILURE;
    }
}
