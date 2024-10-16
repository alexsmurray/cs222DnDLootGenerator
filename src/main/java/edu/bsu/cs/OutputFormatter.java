package edu.bsu.cs;


public class OutputFormatter {
    public static String formatRarity(String rarity) {
        String[] words = rarity.split("\\s");

        StringBuilder output = new StringBuilder();

        for (String word : words) {
            output.append(Character.toTitleCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }

        return output.toString().trim();
    }

    //Change the output to whatever we want it to say
    public static String formatAttunement(String Attunement) {
        if (Attunement.isEmpty()) {
            Attunement = "False";
        } else {
            Attunement = "True";
        }
        return Attunement;
    }


    protected static String formatItemForList(Item item) {
        String formattedLine = "";
        formattedLine += item.getName().substring(0,1).toUpperCase() + item.getName().substring(1).toLowerCase() + "     ";
        formattedLine += item.getRarity().substring(0,1).toUpperCase() + item.getRarity().substring(1).toLowerCase() + "     ";
        formattedLine += item.getType().substring(0,1).toUpperCase() + item.getType().substring(1).toLowerCase();
        if (item.getAttunement().equals("True")) {
            formattedLine += "      *";
        }
        return formattedLine;

    }

}
