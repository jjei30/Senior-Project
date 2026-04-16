package Enemy.EnemyBehaviorTree;

import java.util.List;

public class SequenceNodes implements Nodes{
    private List<Nodes> children;

    public SequenceNodes(List<Nodes> children){
        this.children = children;
    }

    @Override
    public Stat nodeExecute(){
        for(Nodes c : children){
            Stat stat = c.nodeExecute();
            if(stat == Stat.FAILURE){
                return Stat.FAILURE;
            }
           
        }
        return Stat.SUCCESS;
    }
}
