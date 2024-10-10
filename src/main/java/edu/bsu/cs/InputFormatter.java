package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.Random;

public class InputFormatter {
    public static String formatRarity(String rarity) {
        String[] words = rarity.split("\\s");

        StringBuilder output = new StringBuilder();

        for (String word : words) {
            output.append(Character.toTitleCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }

        return output.toString().trim();
    }

    //Change the output to whatever we want it to say
    public static String formatAttunment(String Attunement) {
        if (Attunement.isEmpty()) {
            Attunement = "False";
        } else {
            Attunement = "True";
        }
        return Attunement;
    }


    //Choose random index instead of shuffling method
    public static JSONArray shuffleJsonArray (JSONArray array){
        Random rnd = new Random();
        for (int index = array.size() - 1; index >= 0; index--)
        {
            int j = rnd.nextInt(index + 1);

            Object object = array.get(j);
            array.set(j, array.get(index));
            array.set(index, object);
        }
        return array;
    }
}
