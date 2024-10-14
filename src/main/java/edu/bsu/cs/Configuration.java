package edu.bsu.cs;

public class Configuration {
    private static int numItemsRequested;

    protected static void setNumItemsRequested(int numberOfItems) {
        numItemsRequested = numberOfItems;
    }

    protected static int getNumItemsRequested() {
        return numItemsRequested;
    }

}
