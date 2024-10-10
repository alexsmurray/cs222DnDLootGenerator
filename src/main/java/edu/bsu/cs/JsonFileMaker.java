package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class JsonFileMaker {
    protected void writeMagicItemsJsonToFile() throws IOException, URISyntaxException {
        FileWriter magicItemsApi = new FileWriter("src/main/resources/magicitems.txt");
        magicItemsApi.write(JsonToString.readJsonAsString(APIConnection.connectToAPI("v1/magicitems/?format=json").getInputStream()));
        magicItemsApi.close();
    }
    protected void writeArmorJsonToFile() throws IOException, URISyntaxException {
        FileWriter armorApi = new FileWriter("src/main/resources/armor.txt");
        armorApi.write(JsonToString.readJsonAsString(APIConnection.connectToAPI("v2/armor/").getInputStream()));
        armorApi.close();
    }
    protected void writeWeaponsJsonToFile() throws IOException, URISyntaxException {
        FileWriter weaponsApi = new FileWriter("src/main/resources/weapons.txt");
        weaponsApi.write(JsonToString.readJsonAsString(APIConnection.connectToAPI("v2/weapons/").getInputStream()));
        weaponsApi.close();
    }
}
