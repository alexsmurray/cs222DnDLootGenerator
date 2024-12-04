package edu.bsu.cs;

public class Item {

    private final String name;
    private String rarity = "Mundane";
    private String type = "Standard Item";
    private String attunement = "False";
    private final String details;

    public Item(String name, String details) {
        this.name = name;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    protected Item setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    protected Item setType(String newType) {
        this.type = newType;
        return this;
    }

    protected Item setAttunement(String attunement) {
        this.attunement = attunement;
        return this;
    }

}
