package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonArrayBuilder {

    protected JSONArray buildJsonArrayOfMagicItemPages(String stringifiedJson, String category) {
        JSONArray jsonArray = new JSONArray();
        String[] magicItemJsonArray = stringifiedJson.split("\n");
        for (String magicItemPage : magicItemJsonArray) {
            jsonArray.add(JsonPath.read(magicItemPage, "$.." + category));
        }
        return jsonArray;
    }

}
