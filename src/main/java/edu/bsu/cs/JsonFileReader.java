package edu.bsu.cs;

import java.io.FileInputStream;
import java.io.IOException;

public class JsonFileReader {

    public static String readFileToString(String filePath) throws IOException {
        return JsonToString.readJsonAsString(new FileInputStream(filePath));
    }
}
