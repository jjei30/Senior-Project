package Effects;

import Enemy.Enemy;
import Player.Player;

public class Effects {
    private EffectType effectType;
    private int duration;
    private int amount;

    enum EffectType{
        POISON, BURN, FREEZE, DMG, HEAL
    }

    public Effects(EffectType effectType, int duration, int amount){
        this.effectType = effectType;
        this.duration = duration;
        this.amount = amount;
    }

    public void applyPlayerEffect(Player player, Enemy enemy){
        int playerInt = player.getIntelligence();
        switch(effectType){
            case POISON:
                enemy.takeDamage(amount + playerInt);
                System.out.println("Enemy is poisoned! It deals " + amount + " damage! ");
                break;
            case BURN:
                enemy.takeDamage(amount + 4 + playerInt);
                System.out.println("Enemy is burning! It deals " + amount + " damage! ");
                break;
            case FREEZE:
                //filler
                break;
            case DMG:
                enemy.takeDamage(amount + playerInt);
                System.out.println("Enemy has taken  " + amount + " of damage!");
                break;
            case HEAL:
                player.heal(amount + playerInt);
                System.out.println("Player heals  " + amount + " hp!");
                break;
        }
    }

    public void applyEnemyEffect(Player player, Enemy enemy){
        int enemyInt = enemy.getIntelligence();
        switch(effectType){
            case POISON:
                enemy.takeDamage(amount + enemyInt);
                System.out.println("Player is poisoned! It deals " + amount + " damage! ");
                break;
            case BURN:
                enemy.takeDamage(amount + 4 + enemyInt);
                System.out.println("Player is burning! It deals " + amount + " damage! ");
                break;
            case FREEZE:
                //filler
                break;
            case DMG:
                enemy.takeDamage(amount + enemyInt);
                System.out.println("Player has taken  " + amount + " of damage!");
                break;
            case HEAL:
                enemy.heal(amount + enemyInt);
                System.out.println("Enemy heals  " + amount + " hp!");
                break;
        }
    }

    public void durationTimer(){
        duration--;
    }

    public boolean effectExpired(){
        if(duration <= 0){
            return true;
        }else{
            return false;
        }
    }

    public EffectType getEffectType(){
        return effectType;
    }
}
