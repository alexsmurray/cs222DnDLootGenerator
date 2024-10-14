package edu.bsu.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class JsonFileMaker {

    protected void writeMagicItemsJsonToFile() throws IOException, URISyntaxException {
        if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {return;}

        int pageNum = 1;
        FileWriter magicItemsApi = new FileWriter("src/main/resources/magicitems.txt");
        InputStream inputStream = APIConnection.fetchConnectionPath("v1/magicitems/?format=json&page=" + pageNum).getInputStream();
        StringBuilder magicItemsString = new StringBuilder();
        String inputStreamString = JsonToString.readJsonAsString(inputStream);

        String next = JsonParser.parseNext(inputStreamString);
        while (!next.equals("[null]")) {
            magicItemsString.append(inputStreamString).append("\n");
            pageNum ++;
            inputStream = APIConnection.fetchConnectionPath("v1/magicitems/?format=json&page="+pageNum).getInputStream();
            inputStreamString = JsonToString.readJsonAsString(inputStream);
            next = JsonParser.parseNext(inputStreamString);

        }
        magicItemsString.append(inputStreamString);
        magicItemsApi.write(String.valueOf(magicItemsString));
        magicItemsApi.close();
    }
    protected void writeArmorJsonToFile() throws IOException, URISyntaxException {
        if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {return;}

        FileWriter armorApi = new FileWriter("src/main/resources/armor.txt");
        armorApi.write(JsonToString.readJsonAsString(APIConnection.fetchConnectionPath("v2/armor/").getInputStream()));
        armorApi.close();
    }
    protected void writeWeaponsJsonToFile() throws IOException, URISyntaxException {
        if (ErrorHandler.verifyNetworkConnection().equals("Network Error")) {return;}

        FileWriter weaponsApi = new FileWriter("src/main/resources/weapons.txt");
        weaponsApi.write(JsonToString.readJsonAsString(APIConnection.fetchConnectionPath("v2/weapons/").getInputStream()));
        weaponsApi.close();
    }

}
