package edu.bsu.cs;

public class Armor extends Item {
    private final String name;
    private final String rarity;
    private final String type;
    private final String attunement;

    public Armor(String name, String rarity, String type, String attunement) {
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
    public String getAttunement() {
        return attunement;
    }
}
