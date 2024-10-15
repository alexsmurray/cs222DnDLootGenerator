package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestItemBuilder {
    @Test
    public void testSelectRandomItemGeneratesIndexInRange() {
        JSONArray testJsonArray = createJsonArray();
        int testIndex = ItemBuilder.selectRandomItemIndex(testJsonArray);
        Assertions.assertTrue(testIndex >= 0 && testIndex < testJsonArray.size());
    }

    @Test
    public void testGenerateWeapon() throws IOException {
        if(ErrorHandler.verifyFileExists("src/main/resources/weapons.txt")) {
            ItemBuilder itemBuilder = new ItemBuilder();
            Weapon testWeapon = itemBuilder.generateWeapon();
            Assertions.assertTrue(List.of(SampleWeaponsList.SAMPLE).contains(testWeapon.getName()));
        }
    }


    private JSONArray createJsonArray(){
        JSONArray array = new JSONArray();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        return array;
    }
}
