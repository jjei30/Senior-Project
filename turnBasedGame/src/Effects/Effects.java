package Effects;
import Player.Player;
import Enemy.Enemy;

public class Effects {
    private String typeName;
    private int duration;
    private Player player;
    private Enemy enemy;

    public Effects(String typeName, int duration){
        this.typeName = typeName;
        this.duration = duration;
    }
    public String getTypeName(){
        return typeName;
    }
    public void applyPlayerEffect(){
        switch(typeName){
            case "Poison":
                player.takeDamage(3 + enemy.getIntelligence());
                break;
            case "Burning":
                player.takeDamage(5 + enemy.getIntelligence());
                break;
            case "Freeze":
                //player will be frozen here
                break;
            case "Healing":
                player.heal(20 + player.getIntelligence());
                break;
        }
    }
    public void applyEnemyEffect(){
        switch(typeName){
            case "Poison":
                enemy.takeDamage(3 + player.getIntelligence());
                break;
            case "Freeze":
                //player will be frozen here
                break;
            case "Healing":
                enemy.heal(5 + enemy.getIntelligence());
                break;
        }
    }
    public void durationTime(){
        duration--;
    }
    public boolean isEffectExpired(){
        if(duration <= 0){
            return true;
        }else{
            return false;
        }
    }

}
