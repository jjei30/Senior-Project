package Items;
public class GameItem {
    public static Item smallHealthPotion(){
        Item smallHealthPotion = new Item("Small Health Potion", "Consumable", "A potion that restores 20hp!", 5);
        smallHealthPotion.setHealthRestore(20);
        return smallHealthPotion;
    }
    public static Item MediumHealthPotion(){
        Item mediumHealthPotion = new Item("Medium Health Potion", "Consumable", "A potion that restores 50hp!", 10);
        mediumHealthPotion.setHealthRestore(50);
        return mediumHealthPotion;
    }
    public static Item largeHealthPotion(){
        Item largeHealthPotion = new Item("Large Health Potion", "Consumable", "A potion that restores 100hp!", 20);
        largeHealthPotion.setHealthRestore(100);
        return largeHealthPotion;
    }
    public static Item climbingGear(){
        Item climbingGear = new Item("Climbing Gear", "Gear", "Gear that will make you ready to go on top of the roughest of mountains!", 50);
        climbingGear.setMountainPossible(true);
        return climbingGear;
    }
    public static Item waterBoots(){
        Item waterBoots = new Item("Water Boots", "Gear", "Created by a mysterious Engineer, these boots can get someone to walk on any body of water as if it were a normal floor!", 70);
        waterBoots.setWaterPossible(true);
        return waterBoots;
    }
    public static Item woodenSword(){
        Item woodenSword = new Item("Wooden Sword", "Weapon", "A simple wooden sword, it could still hurt someone", 15);
        
        return woodenSword;
    }
}
