package edu.bsu.cs;


import java.io.IOException;
import java.net.URISyntaxException;


public abstract class Item {
    public abstract String getName(String input) throws URISyntaxException, IOException;

}
