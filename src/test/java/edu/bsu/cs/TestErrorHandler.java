package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("Connected", connectionStatus);
    }

    @Test
    public void testVerifyFileExists(){
        String filePath = "src/main/resources/fxmlSample.fxml";
        Assertions.assertTrue(ErrorHandler.verifyFileExists(filePath));
    }

    @Test
    public void testVerifyAllItemFilesExist(){
        Assertions.assertTrue(ErrorHandler.verifyAllItemFilesExist());
    }
}
