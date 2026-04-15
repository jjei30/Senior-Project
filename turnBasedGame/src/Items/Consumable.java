package Items;

public class Consumable extends Item {
    private int healthRecover;
    private int manaRecover;

    public Consumable(String name, String desc, int value, int healthRecover, int manaRecover){
        super(name, "Consumable",desc,value);
        this.healthRecover = healthRecover;
        this.manaRecover = manaRecover;
    }

    public int getHealthRecover(){
        return healthRecover;
    }

    public int getManaRecover(){
        return manaRecover;
    }
}
