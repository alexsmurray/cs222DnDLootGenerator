package edu.bsu.cs;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class JsonToString {

    public static String readJsonAsString(InputStream file) throws IOException {
        return new String(file.readAllBytes(), Charset.defaultCharset());
    }

}