package Items;
public class Gear extends Item {
    private boolean mountainPossible;
    private boolean waterPossible;

    public Gear(String name, String desc, int value){
        super(name, "Gear", desc, value);
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
}
