package edu.bsu.cs;

public class RarityToIntegerConverter {

    protected static int determineRarityValue (String rarity) {
        return switch (rarity.strip()) {
            case "Legendary" -> 1;
            case "Very Rare" -> 2;
            case "Rare" -> 3;
            case "Uncommon" -> 4;
            case "Common" -> 5;
            case "Mundane" -> 6;
            default -> 0;
        };
    }

}
