package edu.bsu.cs;
import java.util.Dictionary;
import java.util.Hashtable;

public class Weapon extends Item {
    Dictionary<String, String> statDictionary = new Hashtable<>();
    public Weapon(String name, String rarity, String type, String attunement, String details) {
        super(name, rarity, type, attunement, details);
    }

    public Dictionary<String,String> getStatDictionary(){return statDictionary;}




}
