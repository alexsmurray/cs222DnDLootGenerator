package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestVideoURLFetcher {

    String[] urlList = {
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=21",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=59",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=298",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=521",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=543",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=642",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=722",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=881",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=979",
            "https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=1070",
            "https://www.youtube.com/embed/4i8qAZOu5-g?autoplay=1"
        };

    @Test
    public void testGetRandomVideoLocation() {
        String result = VideoURLFetcher.getRandomVideoLocation();
        Assertions.assertTrue(List.of(urlList).contains(result));
    }

}
