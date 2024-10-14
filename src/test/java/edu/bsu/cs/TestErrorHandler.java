package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestErrorHandler {

    @Test
    public void testVerifyNetworkConnection() {
        String connectionStatus = ErrorHandler.verifyNetworkConnection();
        Assertions.assertEquals("Connected", connectionStatus);
    }
}
