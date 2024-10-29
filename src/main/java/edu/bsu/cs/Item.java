package edu.bsu.cs;

public class Item {

    private final String name;
    private String rarity = "Mundane";
    private String type = "Standard Item";
    private String attunement = "None";
    private final String details;


    public Item(String name, String rarity, String type, String attunement, String details) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
        this.details = details;
    }

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

    protected void setType(String newType){
        this.type = newType;
    }
}
