package edu.bsu.cs;


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
        formattedLine += String.format("%s    ",capitalize(item, "Name"));
        formattedLine += String.format("    %s    ",capitalize(item, "Rarity"));
        formattedLine += String.format("    %s    ",capitalize(item, "Type"));
        if (item.getAttunement().equals("True")) {
            formattedLine += "*";
        }
        return formattedLine;

    }

    private static String capitalize(Item item, String category) {
        return switch (category) {
            case "Name" -> item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1).toLowerCase();
            case "Rarity" -> item.getRarity().substring(0, 1).toUpperCase() + item.getRarity().substring(1).toLowerCase();
            case "Type" ->  item.getType().substring(0, 1).toUpperCase() + item.getType().substring(1).toLowerCase();
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };

    }

}
