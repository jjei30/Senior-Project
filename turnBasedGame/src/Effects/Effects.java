package Effects;

import Enemy.Enemy;
import Player.Player;

public class Effects {
    private EffectType effectType;
    private int duration;
    private int amount;
    private int intelligence;
    private boolean effectJustAdded = true;

    public enum EffectType{
        POISON, BURN, FREEZE, DMG, HEAL
    }

    public Effects(EffectType effectType, int duration, int amount, int intelligence){
        this.effectType = effectType;
        this.duration = duration;
        this.amount = amount;
        this.intelligence = intelligence;
    }

    public void applyEnemyEffect(Enemy enemy){
        if(effectJustAdded && effectType != EffectType.FREEZE){
            effectJustAdded = false;
            return;
        }
        effectJustAdded = false;
        switch(effectType){
            case POISON:
                enemy.takeDamage(amount + intelligence);
                System.out.println("Enemy is poisoned! It deals " + amount + " damage! ");
                break;
            case BURN:
                enemy.takeDamage(amount + 4 + intelligence);
                System.out.println("Enemy is burning! It deals " + amount + " damage! ");
                break;
            case FREEZE:
                //filler
                break;
            case DMG:
                enemy.takeDamage(amount + intelligence);
                System.out.println("Enemy has taken  " + amount + " of damage!");
                break;
            case HEAL:
                enemy.heal(amount + intelligence);
                System.out.println("Enemy heals  " + amount + " hp!");
                break;
        }
    }
    public boolean isFrozen(){
        return effectType == EffectType.FREEZE;
    }  

    public void applyPlayerEffect(Player player){
        if(effectJustAdded && effectType != EffectType.FREEZE){
            effectJustAdded = false;
            return;
        }
        effectJustAdded = false;

        switch(effectType){
            case POISON:
                player.takeDamage(amount + intelligence);
                System.out.println("Player is poisoned! It deals " + amount + " damage! ");
                break;
            case BURN:
                player.takeDamage(amount + 4 + intelligence);
                System.out.println("Player is burning! It deals " + amount + " damage! ");
                break;
            case FREEZE:
                System.out.println("Target is frozen and cannot act!");
                break;
            case DMG:
                player.takeDamage(amount + intelligence);
                System.out.println("Player has taken  " + amount + " of damage!");
                break;
            case HEAL:
                player.heal(amount + intelligence);
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
