package Items;
public class Item {
    private String name;
    private String type;
    private String desc;
    private int value;

    public Item(String name, String type, String desc, int value){
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.value = value;
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

    public int getValue(){
        return value;
    }

}
