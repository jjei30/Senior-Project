public class Item {
    private String name;
    private String type;
    private String desc;
    private boolean waterPossible;
    private boolean mountainPossible;
    private int restoreHealth;
    private int restoreMana;

    public Item(String name, String type){
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDesc(){
        return desc;
    }

    public boolean isWaterPossible(){
        return waterPossible;
    }

    public boolean isMountainPossible(){
        return mountainPossible;
    }

    public void setHealthRestore(int amount){
        restoreHealth = amount;
    }

    public void setWaterPossible(boolean bool){
        waterPossible = bool;
    }

    public void setMountainPossible(boolean bool){
        mountainPossible = bool;
    }
}
