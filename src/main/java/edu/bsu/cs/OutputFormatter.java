package edu.bsu.cs;

import java.util.Dictionary;
import java.util.List;

public class OutputFormatter {

    protected static String formatRarity(String rarity) {
        String[] words = rarity.split("\\s");

        StringBuilder output = new StringBuilder();

        for (String word : words) {
            output.append(Character.toTitleCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }

        return output.toString().trim();
    }

    protected static String formatAttunement(String Attunement) {
        if (Attunement.isEmpty()) {
            Attunement = "False";
        } else {
            Attunement = "True";
        }
        return Attunement;
    }

    protected static String formatItemForList(Item item) {
        String formattedLine = "";
        formattedLine += String.format("%s    ", capitalize(item, "Name"));
        formattedLine += String.format("    %s    ", capitalize(item, "Rarity"));
        formattedLine += String.format("    %s    ", capitalize(item, "Type"));
        if (item.getAttunement().equals("True")) {
            formattedLine += "*";
        }
        return formattedLine;

    }

    protected static String formatDateTime(String dateTime) {
        String[] words = dateTime.split("T");

        String output = words[0] + " at " + words[1];

        return output.trim();
    }

    private static String capitalize(Item item, String category) {
        return switch (category) {
            case "Name" -> item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1).toLowerCase();
            case "Rarity" ->
                    item.getRarity().substring(0, 1).toUpperCase() + item.getRarity().substring(1).toLowerCase();
            case "Type" -> item.getType().substring(0, 1).toUpperCase() + item.getType().substring(1).toLowerCase();
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };

    }

    public static String formatArmorStats(Dictionary<Integer, String> statDictionary) {
        StringBuilder armorStats = new StringBuilder();
        String[] statNames = {"AC:  ", "Category:  ", "Stealth Disadvantage:  ", "Strength Score Required:  "};
        for (int i = 0; i < statDictionary.size(); i++) {
            armorStats.append(statNames[i]).append(statDictionary.get(i + 1)).append("\n");
        }
        return armorStats.toString();
    }

    public static String formatWeaponStats(Dictionary<Integer, String> statDictionary) {
        StringBuilder weaponStats = new StringBuilder();
        String[] statNames = {"Is Martial:  ", "Is Simple:  ", "Damage Dice:  ", "Reach:  ", "Damage Type:  ", "Properties:  ", "Is Lance:  ", "Is Net:  "};
        for (int i = 0; i < statDictionary.size(); i++) {
            if (!statDictionary.get(i + 1).equals("false") && !(statDictionary.get(i + 1).equals("0.0")) && !statDictionary.get(i + 1).equals("[]")) {
                weaponStats.append(statNames[i]).append(statDictionary.get(i + 1)).append("\n");
            }
        }
        return weaponStats.toString();
    }

    public static Object formatWeaponDamageType(Object weapon) {
        return weapon.toString().substring(38, weapon.toString().length() - 13);
    }

    public static String formatProperties(List<String> listOfProperties) {
        if (listOfProperties.isEmpty()) {
            return "[]";
        }
        StringBuilder properties = new StringBuilder();
        int counter = 0;
        properties.append("[");
        for (int i = 0; i < listOfProperties.size() - 1; i++) {
            properties.append(listOfProperties.get(i)).append(", ");
            counter++;
        }
        properties.append(listOfProperties.get(counter)).append("]");
        return properties.toString();
    }

    protected static String formatDexModifier(String dexMod) {
        if (dexMod.equals("None")) {
            return "";
        }
        return " +  Dex Modifier " + dexMod;
    }

}
