package edu.bsu.cs;

import java.util.HashMap;
import java.util.Random;

public class VideoURLFetcher {

    static HashMap<Integer,String> hashMap = new HashMap<>();
    static {
        hashMap.put(1,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=21");
        hashMap.put(2,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=59");
        hashMap.put(3,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=298");
        hashMap.put(4,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=521");
        hashMap.put(5,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=543");
        hashMap.put(6,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=642");
        hashMap.put(7,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=722");
        hashMap.put(8,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=881");
        hashMap.put(9,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=979");
        hashMap.put(10,"https://www.youtube.com/embed/aBOH8YLUPjE?autoplay=1&start=1070");
        hashMap.put(11, "https://www.youtube.com/embed/4i8qAZOu5-g?autoplay=1");
    }

    protected static String getRandomVideoLocation() {
        return switch (new Random().nextInt(1, 10)) {
            case 1 -> hashMap.get(1);
            case 2 -> hashMap.get(2);
            case 3 -> hashMap.get(3);
            case 4 -> hashMap.get(4);
            case 5 -> hashMap.get(5);
            case 6 -> hashMap.get(6);
            case 7 -> hashMap.get(7);
            case 8 -> hashMap.get(8);
            case 9 -> hashMap.get(9);
            case 10 -> hashMap.get(10);
            default -> hashMap.get(11);
        };
    }
}
