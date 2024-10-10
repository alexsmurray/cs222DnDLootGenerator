package edu.bsu.cs;

public class Weapon extends Item {
    private String name;
    private String rarity;

    public Weapon(String name, String rarity){
        this.name = name;
        this.rarity = rarity;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRarity() {
        return null;
    }
}

