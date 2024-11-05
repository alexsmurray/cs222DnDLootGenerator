package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestJsonFileReader {

    @Test
    public void testSampleFileRead() throws IOException {
        StringWriter stringWriter = new StringWriter();
        FileInputStream testFileInputStream = new FileInputStream("src/test/resources/SampleMagicItem.json");
        InputStreamReader inputStreamReader = new InputStreamReader(testFileInputStream, StandardCharsets.UTF_8);
        inputStreamReader.transferTo(stringWriter);
        String result = stringWriter.toString().substring(0, 95);
        Assertions.assertEquals(result, MagicItemsListString.SAMPLE);
    }

    @Test
    public void testFileIsNotBlank() throws IOException {
            String result = JsonFileReader.readFileToString("src/main/resources/MainScreen.fxml");
            Assertions.assertFalse(result.isBlank());
    }

}
