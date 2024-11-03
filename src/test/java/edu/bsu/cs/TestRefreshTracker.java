package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class TestRefreshTracker {

    @Test
    public void testGetCurrentTime() throws IOException {
        String fileName = "src/test/resources/testLastRefreshTime.txt";
        RefreshTracker.saveCurrentTime(fileName);
        String dateTime = RefreshTracker.readTimeFile(fileName);
        String expected = LocalDateTime.now().toString();
        Assertions.assertEquals(expected.substring(0,4),dateTime.substring(0,4));
    }

}
