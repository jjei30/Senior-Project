package Items;
public class Gear extends Item {
    private boolean mountainPossible;
    private boolean waterPossible;
    private int uses;

    public Gear(String name, String desc, int value, int uses){
        super(name, "Gear", desc, value);
        this.uses = uses;
    }

    public void setMountainPossible(boolean val){
        this.mountainPossible = val;
    }
    public boolean getMountainPossible(){
        return mountainPossible;
    }

    public void setWaterPossible(boolean val){
        this.waterPossible = val;
    }

    public boolean getWaterPossible(){
        return waterPossible;
    }

    public int getUses(){
        return uses;
    }

    public void useGear(){
        uses--;
    }

    public boolean isGearBroken(){
        return uses <= 0;
    }
}
