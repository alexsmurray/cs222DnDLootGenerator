package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("https://api.open5e.com/", connectionStatus);
    }

    @Test
    public void testVerifyFileExists(){
        String filePath = "src/main/resources/mainApp.fxml";
        Assertions.assertTrue(ErrorHandler.verifyFileExists(filePath));
    }

    @Test
    public void testVerifyAllItemFilesExist() throws IOException, URISyntaxException {
        createMissingFiles();
        Assertions.assertTrue(ErrorHandler.verifyAllItemFilesExist());
    }

    private void createMissingFiles() throws IOException, URISyntaxException {
        JsonFileMaker jsonFileMaker = new JsonFileMaker();
        if (!ErrorHandler.verifyFileExists("src/main/resources/armor.txt")) {jsonFileMaker.writeItemsJsonToFile("armor");}
        if (!ErrorHandler.verifyFileExists("src/main/resources/weapons.txt")) {jsonFileMaker.writeItemsJsonToFile("weapons");}
        if (!ErrorHandler.verifyFileExists("src/main/resources/magicitems.txt")) {jsonFileMaker.writeItemsJsonToFile("magicitems");}
    }
    @Test
    public void testVerifyInputIsBlank() {
        String userInput = "";
        boolean result = ErrorHandler.verifyInputIsValid(userInput);
        Assertions.assertFalse(result);
    }

    @Test
    public void testVerifyInputIsAnInteger() {
        String userInput = "10";
        boolean result = ErrorHandler.verifyInputIsValid(userInput);
        Assertions.assertTrue(result);
    }
}
