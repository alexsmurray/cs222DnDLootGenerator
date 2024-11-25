package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

public class ConfigurationFileWriter {

    protected void initializeConfigFile(Hashtable<String, String> configurationValues) throws IOException {
        Files.createDirectories(Paths.get("src/main/resources/dataFiles"));

        writeConfigurationFile(configurationValues);
    }

    protected void writeConfigurationFile(Hashtable<String, String> configurationValues) throws IOException {
        FileWriter configFileWriter = new FileWriter("src/main/resources/dataFiles/configuration.txt");
        clearConfigFile(configFileWriter);
        writeTableToFile(configurationValues, configFileWriter);
        configFileWriter.close();
    }

    private void clearConfigFile(FileWriter configFileWriter) throws IOException {
        configFileWriter.write("");
    }

    private void writeTableToFile(Hashtable<String, String> configurationValues, FileWriter configFileWriter) throws IOException {
        configFileWriter.append(configurationValues.get("rarity")).append(", ");
        configFileWriter.append(configurationValues.get("weight")).append(", ");
        configFileWriter.append(configurationValues.get("armor")).append(", ");
        configFileWriter.append(configurationValues.get("weapons")).append(", ");
        configFileWriter.append(configurationValues.get("magicEquipment"));
    }

}
