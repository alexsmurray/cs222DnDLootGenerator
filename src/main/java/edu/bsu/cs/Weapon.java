package edu.bsu.cs;

public class Weapon extends Item {
    private String name;

    public Weapon(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}

