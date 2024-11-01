package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

public class NextPageParser {

    protected String parseNext(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..next").toString();
    }

}
