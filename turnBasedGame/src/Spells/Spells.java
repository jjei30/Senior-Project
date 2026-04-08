package Spells;

import Player.Player;
import Enemy.Enemy;
import Effects.Effects;
import Effects.Effects.EffectType;

public class Spells {
    private String spellName;
    private int manaCost;
    private int power;
    private Effects.EffectType effectType;

    public Spells(String spellName, int manaCost, int power, EffectType effectType){
        this.spellName = spellName;
        this.manaCost = manaCost;
        this.power = power;
        this.effectType = effectType;
    }
    public String getSpellName(){
        return spellName;
    }

    public void playerSpellCast(Player player, Enemy enemy, String spellName){
        if(player.getMana() < manaCost){
            System.out.println("Not enough Mana!");
            return;
        }else{
            player.manaDrain(manaCost);
            switch(effectType){
            case POISON:
                enemy.effectAdd(new Effects(effectType, 3, power));
                break;
            case BURN:
                enemy.effectAdd(new Effects(effectType, 3, power));
                break;
            case FREEZE:
                //filler
                break;
            case DMG:
                enemy.effectAdd(new Effects(effectType, 3, power));
                break;
            case HEAL:
                player.effectAdd(new Effects(effectType, 3, power));
                break;
            }
        }
    }

    public void enemySpellCast(Player player, Enemy enemy, String spellName){
        if(enemy.getMana() < manaCost){
            System.out.println("Enemy does not have enough Mana!");
            return;
        }else{
            enemy.manaDrain(manaCost);
            switch(effectType){
            case POISON:
                player.effectAdd(new Effects(effectType, 3, power));
                break;
            case BURN:
                player.effectAdd(new Effects(effectType, 3, power));
                break;
            case FREEZE:
                //filler
                break;
            case DMG:
                player.effectAdd(new Effects(effectType, 3, power));
                break;
            case HEAL:
                player.effectAdd(new Effects(effectType, 3, power));
                break;
            }
        }
    }
}

