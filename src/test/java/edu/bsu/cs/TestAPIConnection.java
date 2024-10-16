package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class TestAPIConnection {

    @Test
    public void testFetchConnectionPath() throws URISyntaxException, IOException {
        URLConnection urlConnection = APIConnection.fetchConnectionPath("");
        Assertions.assertNotNull(urlConnection);
    }

    @Test
    public void testConnectionReturnsJson() throws URISyntaxException, IOException {
        URLConnection urlConnection = APIConnection.fetchConnectionPath("");
        String stringifiedJson = readJsonSampleAsString(urlConnection);
        Assertions.assertInstanceOf(JSONArray.class, getJsonSample(stringifiedJson));
    }

    private String readJsonSampleAsString(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private JSONArray getJsonSample(String stringifiedJson) {
        return JsonPath.read(stringifiedJson, "$..*");
    }

}
