package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("Connected", connectionStatus);
    }

    @Test
    public void testVerifyFileExists() throws IOException {
        String filePath = "src/main/resources/fxmlSample.fxml";
        Assertions.assertTrue(ErrorHandler.verifyFileExists(filePath));
    }

    @Test
    public void testVerifyAllItemFilesExist() throws IOException {
        Assertions.assertTrue(ErrorHandler.verifyAllItemFilesExist());
    }
}
