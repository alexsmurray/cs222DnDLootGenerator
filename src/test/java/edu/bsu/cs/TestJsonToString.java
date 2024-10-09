package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class TestJsonToString {

    @Test
    public void testGetNameFromSampleJson() throws IOException {
        JSONArray sampleJsonArray = getNameFromJsonSample(readJsonSampleAsString());
        String[] expected = {"Aberrant Agreement","Absurdist Web","Accursed Idol","Adamantine Armor","Aegis of the Eternal Moon"};
        String[] result = new String[5];
        for (int i = 0; i < 5; i++) {
            result[i] = sampleJsonArray.get(i).toString();
        }
        Assertions.assertArrayEquals(expected,result);
    }

    private String readJsonSampleAsString() throws IOException {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("SampleMagicItemPage1.json")){
            return new String (Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        }
    }

    private JSONArray getNameFromJsonSample(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..name");
    }

}
