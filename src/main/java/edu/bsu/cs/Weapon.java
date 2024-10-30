package edu.bsu.cs;
import java.util.Dictionary;
import java.util.Hashtable;

public class Weapon extends Item {
    Dictionary<String, String> statDictionary = new Hashtable<>();
    public Weapon(String name, String rarity, String type, String attunement, String details, String isVersitile, String isMartial, String isMelee, String rangedAttackPossible, String hasReach, String distanceUnit, String damageDice, String versitileDice, String reach, String range, String longRange, String isFinesse, String isThrown, String isTwoHanded, String requiresAmmunation, String requiresLoading, String isHeavy, String isLight, String isLance, String isNet, String isSimple, String isImprovised) {
        super(name, rarity, type, attunement, details);
    }

    public Dictionary<String,String> getStatDictionary(){return statDictionary;}




}
