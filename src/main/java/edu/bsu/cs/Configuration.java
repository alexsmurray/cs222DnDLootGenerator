package edu.bsu.cs;

public class Configuration {
    private static int randomGenerationWeight;

    public static int getRandomGenerationWeight() { return randomGenerationWeight; }

    public void setRandomGenerationWeight(int weight) { randomGenerationWeight = weight; }
}
