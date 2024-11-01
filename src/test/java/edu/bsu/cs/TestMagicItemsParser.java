package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMagicItemsParser {

    MagicItemsParser testMagicParser = new MagicItemsParser();

    @Test
    public void testGetMagicItemNameFromSampleJson() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        JSONArray sampleJsonArray = testMagicParser.parseMagicItemName(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Aberrant Agreement","Absurdist Web","Accursed Idol","Adamantine Armor","Aegis of the Eternal Moon"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetRarityFromSampleJson() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        JSONArray sampleJsonArray = testMagicParser.parseMagicItemRarity(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Rare","Very Rare","Uncommon","Uncommon","Very Rare"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = OutputFormatter.formatRarity(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetTypeFromSampleJson() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        JSONArray sampleJsonArray = testMagicParser.parseMagicItemType(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"Scroll","Wondrous Item","Wondrous item","Armor (medium or heavy)","Armor"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetAttunementFromSampleJson() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        JSONArray sampleJsonArray = testMagicParser.parseMagicItemAttunement(JsonToString.readJsonAsString(testInputStream), 0);
        String[] expected = {"False","False","True","False","True"};
        String [] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = OutputFormatter.formatAttunement(sampleJsonArray.get(i).toString());
        }
        Assertions.assertArrayEquals(expected,result);
    }

    @Test
    public void testGetDescriptionFromSampleJson() throws IOException {
        InputStream testInputStream = getMagicItemJsonFile();
        JSONArray sampleJsonArray = testMagicParser.parseMagicItemDescription(JsonToString.readJsonAsString(testInputStream), 0);
        String expected = "This long scroll bears strange runes and seals of eldritch powers. When you use an action to present this scroll to an aberration whose Challenge Rating is equal to or less than your level, the binding powers of the scroll compel it to listen to you. You can then attempt to strike a bargain with the aberration, negotiating a service from it in exchange for a reward. The aberration is under no compulsion to strike the bargain; it is compelled only to parley long enough for you to present a bargain and allow for negotiations. If you or your allies attack or otherwise attempt to harm the aberration, the truce is broken, and the creature can act normally. If the aberration refuses the offer, it is free to take any actions it wishes. Should you and the aberration reach an agreement that is satisfactory to both parties, you must sign the agreement and have the aberration do likewise (or make its mark, if it has no form of writing). The writing on the scroll changes to reflect the terms of the agreement struck. The magic of the charter holds both you and the aberration to the agreement until its service is rendered and the reward paid, at which point the scroll blackens and crumbles to dust. An aberration's thinking is alien to most humanoids, and vaguely worded contracts may result in unintended consequences, as the creature may have different thoughts as to how to best meet the goal. If either party breaks the bargain, that creature immediately takes 10d6 psychic damage, and the charter is destroyed, ending the contract.";
        String result = sampleJsonArray.getFirst().toString();
        Assertions.assertEquals(expected,result);
    }

    private InputStream getMagicItemJsonFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleMagicItem.json");
        assert inputStream != null;
        return inputStream;
    }
}
