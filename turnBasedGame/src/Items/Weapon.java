package Items;
public class Weapon extends Item {
    private int dmg;

    public Weapon(String name, String desc, int value, int dmg){
        super(name, "Weapon",desc,value);
        this.dmg = dmg;
    }

    public int getDmg(){
        return dmg;
    }
}
