package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.Random;

public class StandardItemBuilder {
    protected static int selectRandomItem(JSONArray array){
        return new Random().nextInt(array.size());
    }

}
