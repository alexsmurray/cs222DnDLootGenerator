package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestController {

    @Test
    public void TestJsonStringIsReturned() throws IOException, URISyntaxException {
        String result = Controller.fetchStringifiedJson("");
        Assertions.assertNotNull(result);
    }
}
