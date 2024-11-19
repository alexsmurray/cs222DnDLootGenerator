package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.Dictionary;
import java.util.Hashtable;

public class WeaponItemParser {

    private final String stringifiedJson;

    public WeaponItemParser(String stringifiedJson) {
        this.stringifiedJson = stringifiedJson;
    }

    protected JSONArray parseWeaponItemName(){
        return JsonPath.read(stringifiedJson, "$..name");
    }

    protected JSONArray parseWeaponIsMartial(){
        return JsonPath.read(stringifiedJson, "$..is_martial");
    }

    protected JSONArray parseWeaponProperties(){
        return JsonPath.read(stringifiedJson, "$..properties");
    }

    protected JSONArray parseDamageDice(){
        return JsonPath.read(stringifiedJson, "$..damage_dice");
    }

    protected JSONArray parseWeaponReach(){
        return JsonPath.read(stringifiedJson, "$..reach");
    }

    protected JSONArray parseWeaponIsLance(){
        return JsonPath.read(stringifiedJson, "$..is_lance");
    }

    protected JSONArray parseWeaponIsNet(){
        return JsonPath.read(stringifiedJson, "$..is_net");
    }

    protected JSONArray parseWeaponIsSimple(){
        return JsonPath.read(stringifiedJson, "$..is_simple");
    }

    protected JSONArray parseWeaponDamageType(){
        return JsonPath.read(stringifiedJson, "$..damage_type");
    }

    protected Dictionary<Integer, String> parseAllWeaponStats(int randomIndex) {
        Dictionary<Integer, String> statDictionary = new Hashtable<>();

        statDictionary.put(1, parseWeaponIsMartial().get(randomIndex).toString());
        statDictionary.put(2, parseWeaponIsSimple().get(randomIndex).toString());
        statDictionary.put(3, parseDamageDice().get(randomIndex).toString());
        statDictionary.put(4, parseWeaponReach().get(randomIndex).toString());
        statDictionary.put(5, OutputFormatter.formatWeaponDamageType(parseWeaponDamageType().get(randomIndex)).toString());
        statDictionary.put(6, parseWeaponProperties().get(randomIndex).toString().replace("\\",""));
        statDictionary.put(7, parseWeaponIsLance().get(randomIndex).toString());
        statDictionary.put(8, parseWeaponIsNet().get(randomIndex).toString());

        return statDictionary;
    }

}
