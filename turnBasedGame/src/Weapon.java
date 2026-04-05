public class Weapon extends Item {
    private int dmg;

    public Weapons(String name, String description, int value, int dmg){
        super(name, "Weapon",description,value);
        this.dmg = dmg;
    }

    public int getDmg(){
        return dmg;
    }
}
