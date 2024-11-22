package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("https://api.open5e.com/", connectionStatus);
    }

    @Test
    public void testVerifyFileExists(){
        String filePath = "src/main/resources/MainScreen.fxml";
        Assertions.assertTrue(ErrorHandler.verifyFileExists(filePath));
    }

    @Test
    public void testVerifyFileHasContents() throws IOException {
        String filePath = "src/test/resources/SampleMagicItem.json";
        Assertions.assertTrue(ErrorHandler.verifyFileHasContents(filePath));
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

    @Test
    public void testVerifyHomebrewInputIsNotBlank() {
        String[] checkedInputs = {"   ",""};
        Assertions.assertFalse(ErrorHandler.verifyHomebrewInputsNotBlank(checkedInputs));
    }

}
