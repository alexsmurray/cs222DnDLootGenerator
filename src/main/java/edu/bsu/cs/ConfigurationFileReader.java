package edu.bsu.cs;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class ConfigurationFileReader {
    protected String readConfigFileAsString() throws IOException {
        FileInputStream testConfigReader = new FileInputStream("src/main/resources/dataFiles/configuration.txt");
        String configurationFileString = new String(testConfigReader.readAllBytes(), Charset.defaultCharset());
        testConfigReader.close();
        return configurationFileString;
    }
}
