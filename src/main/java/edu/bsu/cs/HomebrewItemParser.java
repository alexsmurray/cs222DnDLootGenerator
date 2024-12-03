package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.util.Hashtable;

public class HomebrewItemParser {

    private final String stringifiedJson;

    public HomebrewItemParser(String stringifiedJson) {
        this.stringifiedJson = stringifiedJson;
    }

    protected Hashtable<String, String> parseAllHomebrewItemDetails(int counter) {
        Hashtable<String, String> homebrewItemDetailsList = new Hashtable<>();
        String[] attributes = {"Item_Type", "Rarity", "Attunement", "Description"};

        for(String attribute : attributes) {
            homebrewItemDetailsList.put(attribute, parseHomebrewAttribute(attribute,counter));
        }
        return homebrewItemDetailsList;
    }

    protected String parseHomebrewAttribute(String attribute,int counter) {
        return buildJsonArrayOfHomebrewItems(attribute).get(counter).toString();
    }

    protected JSONArray buildJsonArrayOfHomebrewItemsNames(){
        return buildJsonArrayOfHomebrewItems("Name");
    }

    protected JSONArray buildJsonArrayOfHomebrewItems(String category){
        return JsonPath.read(stringifiedJson,"$.." + category);
    }

}
