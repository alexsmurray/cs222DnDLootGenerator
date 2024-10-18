package edu.bsu.cs;

import java.net.URI;
import java.net.URLConnection;
import java.util.LinkedList;

public class ErrorHandler {

    protected static String verifyNetworkConnection() {
        URLConnection url;
        String urlName="https://api.open5e.com/";
        try {
            url = new URI(urlName).toURL().openConnection();
            url.connect();
        } catch (Exception NetworkError) {
            return "Network Error";
        }
        return urlName;
    }

    public static boolean verifyFileExists(String filePath) {
        try {
            JsonFileReader.readFileToString(filePath);
        } catch (Exception ReadFileException) {
            return false;
        }
        return true;
    }

    public static boolean verifyAllItemFilesExist() {
        LinkedList<String> filePathList  = new LinkedList<>();
        filePathList.add("src/main/resources/armor.txt");
        filePathList.add("src/main/resources/weapons.txt");
        filePathList.add("src/main/resources/magicitems.txt");

        for (String filePath : filePathList) {
            if (!ErrorHandler.verifyFileExists(filePath)) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyInputIsValid(String userInput) {
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
}
