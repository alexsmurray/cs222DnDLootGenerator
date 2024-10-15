package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestConfiguration {

    @Test
    public void testSetNumItemsRequested() {
        Configuration.setNumItemsRequested(10);
        Assertions.assertEquals(10, Configuration.getNumItemsRequested());
    }

}
