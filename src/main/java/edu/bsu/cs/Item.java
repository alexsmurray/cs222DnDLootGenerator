package edu.bsu.cs;

public class Item {

    private String name = "Item";
    private String rarity = "None";
    private String type = "Standard Item";
    private String attunement = "None";
    private String description = "No Description";
    private String stats = "No Stats";


    public Item(String name, String rarity, String type, String attunement, String description, String stats){
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
        this.description = description;
        this.stats = stats;
    }

    public Item(String name, String stats){

        this.name = name;
        this.stats = stats;
    }

    public Item(String name, String rarity, String type, String attunement, String description) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
        this.description = description;
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
