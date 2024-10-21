package edu.bsu.cs;

public class Item {

    private final String name;
    private final String rarity;
    private final String type;
    private final String attunement;


    public Item(String name, String rarity, String type, String attunement){
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.attunement = attunement;
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


}
