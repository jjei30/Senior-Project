package Items;

public class GameItem {

    public static Consumable smallHealthPotion(){
        Consumable smallHealthPotion = new Consumable("Small Health Potion", "A potion that restores 20hp!", 5, 20, 0);
        return smallHealthPotion;
    }
    public static Consumable mediumHealthPotion(){
        Consumable mediumHealthPotion = new Consumable("Medium Health Potion", "A potion that restores 50hp!", 10, 50, 0);
        return mediumHealthPotion;
    }
    public static Consumable largeHealthPotion(){
        Consumable largeHealthPotion = new Consumable("Large Health Potion", "A potion that restores 100hp!", 20, 100, 0);
        return largeHealthPotion;
    }
    public static Consumable smallManaPotion(){
        Consumable smallManaPotion = new Consumable("Small Mana Potion", "A potion that restores 3 mana!", 5, 0, 3);
        return smallManaPotion;
    }
    public static Consumable mediumManaPotion(){
        Consumable mediumManaPotion = new Consumable("Medium Mana Potion", "A potion that restores 15 mana!", 15, 0, 15);
        return mediumManaPotion;
    }
    public static Consumable largeManaPotion(){
        Consumable largeManaPotion = new Consumable("Large Mana Potion", "A potion that restores 30 mana!", 30, 0, 30);
        return largeManaPotion;
    }
    public static Gear climbingGear(){
        Gear climbingGear = new Gear("Climbing Gear", "Gear that will make you ready to go on top of the roughest of mountains!", 50);
        climbingGear.setMountainPossible(true);
        return climbingGear;
    }
    public static Gear waterBoots(){
        Gear waterBoots = new Gear("Water Boots", "Created by a mysterious Engineer, these boots can get someone to walk on any body of water as if it were a normal floor!", 70);
        waterBoots.setWaterPossible(true);
        return waterBoots;
    }
    public static Weapon woodenStaff(){
        Weapon woodenStaff = new Weapon("Wooden Staff", "A simple wooden staff, it could still hurt someone", 15, 5);
        return woodenStaff;
    }
    public static Weapon ironSword(){
        Weapon ironSword = new Weapon("Iron Sword", "The good old fashioned Iron Sword, used by knights for combat", 50, 15);
        return ironSword;
    }
}
