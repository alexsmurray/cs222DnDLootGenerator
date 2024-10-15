package edu.bsu.cs;

import java.util.LinkedList;

public class ErrorHandler {

    protected static String verifyNetworkConnection() {
        try {
            APIConnection.fetchConnectionPath("").connect();
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
}
