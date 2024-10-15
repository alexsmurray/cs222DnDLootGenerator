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
    public void testGenerateItem() throws IOException {
        if(ErrorHandler.verifyFileExists("src/main/resources/weapons.txt")) {
            ItemBuilder itemBuilder = new ItemBuilder();
            Item testItem = itemBuilder.generateItem("Any");
            Assertions.assertFalse(testItem.getName().isBlank());
        }
    }

    @Test
    public void testGenerateWeapon() throws IOException {
        if(ErrorHandler.verifyFileExists("src/main/resources/weapons.txt")) {
            ItemBuilder itemBuilder = new ItemBuilder();
            Item testWeapon = itemBuilder.generateItem("Weapon");
            Assertions.assertTrue(List.of(WeaponsListString.SAMPLE).contains(testWeapon.getName()));
        }
    }

    @Test
    public void testGenerateArmor() throws IOException {
        if(ErrorHandler.verifyFileExists("src/main/resources/armor.txt")) {
            ItemBuilder itemBuilder = new ItemBuilder();
            Item testArmor = itemBuilder.generateItem("Armor");
            Assertions.assertTrue(List.of(ArmorListString.SAMPLE).contains(testArmor.getName()));
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
