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
        ItemBuilder itemBuilder = new ItemBuilder();
        Item testWeapon = itemBuilder.generateWeapon("src/test/resources/SampleWeapons.json");
        Assertions.assertTrue(List.of(WeaponsListStringArray.SAMPLE).contains(testWeapon.getName()));
    }

    @Test
    public void testGenerateArmor() throws IOException {
        ItemBuilder itemBuilder = new ItemBuilder();
        Item testArmor = itemBuilder.generateArmor("src/test/resources/SampleArmor.json");
        Assertions.assertTrue(List.of(ArmorListStringArray.SAMPLE).contains(testArmor.getName()));
    }

    @Test
    public void testGenerateMagicItem() throws IOException {
        ItemBuilder itemBuilder = new ItemBuilder();
        Item testMagicItem = itemBuilder.generateMagicItem("src/test/resources/SampleMagicItem.json");
        Assertions.assertNotNull(testMagicItem.getName());
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
