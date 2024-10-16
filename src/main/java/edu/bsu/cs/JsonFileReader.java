package edu.bsu.cs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class JsonFileReader {

    public static String readFileToString(String filePath) throws IOException {
        StringWriter stringWriter = new StringWriter();
        FileInputStream testFileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(testFileInputStream, StandardCharsets.UTF_8);
        inputStreamReader.transferTo(stringWriter);
        return stringWriter.toString().replaceAll("\\r\\n?", "\n");

    }
}
