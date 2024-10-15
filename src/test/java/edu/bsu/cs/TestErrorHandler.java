package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("Connected", connectionStatus);
    }

    @Test
    public void testVerifyFileExists() throws IOException {
        String filePath = "src/main/resources/fxmlSample.fxml";
        Assertions.assertNotNull(JsonFileReader.readFileToString(filePath));
    }
}
