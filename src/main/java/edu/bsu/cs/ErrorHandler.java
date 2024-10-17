package edu.bsu.cs;

import java.net.URI;
import java.util.LinkedList;

public class ErrorHandler {

    protected static String verifyNetworkConnection() {
        try {
            new URI("https://api.open5e.com/").toURL().openConnection();
        } catch (Exception NetworkError) {
            return "Network Error";
        }
        return "Connected";
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
            if (userInputNumber <= 0) {
                throw new Exception();
            }
        } catch (Exception InputException) {
            return false;
        }
        return true;
    }
}
