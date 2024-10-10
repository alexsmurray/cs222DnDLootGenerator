package edu.bsu.cs;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class JsonToString {
    public static String readJsonAsString(InputStream file) throws IOException {
        return new String(Objects.requireNonNull(file).readAllBytes(), Charset.defaultCharset());
    }


}