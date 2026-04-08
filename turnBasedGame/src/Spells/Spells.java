package Spells;

import Player.Player;
import Enemy.Enemy;

public class Spells {
    private String spellName;
    private int manaCost;
    private int power;
    private String spellType;
    private Player player;
    private Enemy enemy;

    public Spells(String spellName, int manaCost, int power, String spellType){
        this.spellName = spellName;
        this.manaCost = manaCost;
        this.power = power;
        this.spellType = spellType;
    }

    public void playerSpellCast(Player player, Enemy enemy, String spellName){
        if(player.getMana() < manaCost){
            System.out.println("Not enough Mana!");
            return;
        }else{
            player.manaDrain(manaCost);
            switch(spellName){
            case "Poison":
                enemy.takeDamage(5 + player.getIntelligence());
                break;
            case "Fire":
                enemy.takeDamage(10+player.getIntelligence());
                break;
            case "Ice":
                //player will be frozen here
                break;
            case "Heal":
                player.heal(20 + player.getIntelligence());
                break;
        }
        }
    }
}
