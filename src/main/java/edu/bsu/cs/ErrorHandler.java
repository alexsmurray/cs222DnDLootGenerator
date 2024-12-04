package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;
import java.util.LinkedList;

public class ErrorHandler {

    protected static String verifyNetworkConnection() {
        URLConnection url;
        String urlName = "https://api.open5e.com/";
        try {
            url = new URI(urlName).toURL().openConnection();
            url.connect();
        } catch (Exception NetworkError) {
            return "Network Error";
        }
        return urlName;
    }

    protected static boolean verifyFileExists(String filePath) {
        try {
            JsonFileReader.readFileToString(filePath);
        } catch (Exception ReadFileException) {
            return false;
        }
        return true;
    }

    public static boolean verifyFileHasContents(String filePath) throws IOException {
        String fileString = JsonFileReader.readFileToString(filePath);
        return fileString.startsWith("{");
    }

    protected static boolean verifyItemDataFilesValid() throws IOException {
        LinkedList<String> filePathList = new LinkedList<>();
        filePathList.add("src/main/resources/dataFiles/armor.txt");
        filePathList.add("src/main/resources/dataFiles/weapons.txt");
        filePathList.add("src/main/resources/dataFiles/magicitems.txt");

        for (String filePath : filePathList) {
            if (!ErrorHandler.verifyFileExists(filePath)) {
                return false;
            }
            if (!ErrorHandler.verifyFileHasContents(filePath)) {
                return false;
            }
        }
        return true;
    }

    protected static boolean verifyInputIsValid(String userInput) {
        try {
            int userInputNumber = Integer.parseInt(userInput);
            if (userInputNumber <= 0 || userInputNumber > 100) {
                throw new Exception();
            }
        } catch (Exception InputException) {
            return false;
        }
        return true;
    }

    public static boolean verifyHomebrewInputsNotBlank(String[] checkedInputs) {
        for (String input : checkedInputs) {
            if (input.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
