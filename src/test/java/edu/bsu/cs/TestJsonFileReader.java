package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestJsonFileReader {
    @Test
    public void testSampleFileIsNotBlank() throws IOException {
        StringWriter stringWriter = new StringWriter();
        FileInputStream testFileInputStream = new FileInputStream("src/test/resources/SampleMagicItemPage1.json");
        InputStreamReader inputStreamReader = new InputStreamReader(testFileInputStream, StandardCharsets.UTF_8);
        inputStreamReader.transferTo(stringWriter);
        String result = stringWriter.toString().replaceAll("\\r\\n?", "\n");
        Assertions.assertEquals(result, SampleMagicString.SAMPLE);
    }
}
