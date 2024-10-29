package edu.bsu.cs;

public class Armor extends Item {
    private final String armorClass;
    private final String armorCategory;
    private final boolean stealthDisadvantage;
    private final String strengthScoreRequirement;

    public Armor(String name, String rarity, String type, String attunement, String details, String armorClass, String armorCategory, boolean stealthDisadvantage, String strengthScoreRequirement) {
        super(name, rarity, type, attunement, details);
        this.armorClass = armorClass;
        this.armorCategory = armorCategory;
        this.stealthDisadvantage = stealthDisadvantage;
        this.strengthScoreRequirement = strengthScoreRequirement;
    }

    public String getArmorClass(){return armorClass;}

    public String getArmorCategory(){return armorCategory;}

    public boolean getStealthDisadvantage(){return stealthDisadvantage;}

    public String getStrengthScoreRequirement(){return strengthScoreRequirement;}

}
