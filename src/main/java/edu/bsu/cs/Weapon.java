package edu.bsu.cs;

public class Weapon extends Item {
    private final String name;
    private final String rarity;
    private final String type;
    private final boolean attunement;


    public Weapon(String name, String rarity, String type, boolean attunement){
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRarity() {
        return rarity;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean getAttunement() {
        return attunement;
    }
}

