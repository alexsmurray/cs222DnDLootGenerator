package edu.bsu.cs;

public class Item {

    private final String name;
    private final String rarity;
    private final String type;
    private final String attunement;
    private final String description;
    private final String stats;


    public Item(String name, String rarity, String type, String attunement, String description, String stats){
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
        this.description = description;
        this.stats = stats;
    }
    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getType() {
        return type;
    }

    public String getAttunement() {
        return attunement;
    }

    public String getDescriptions() { return description; }

    public String getStats() { return stats; }
}
