package edu.bsu.cs;

public class ErrorHandler {

    protected static String verifyNetworkConnection() {
        try {
            APIConnection.fetchConnectionPath("").connect();
        } catch (Exception NetworkError) {
            return "Network Error";
        }
        return "Connected";
    }
}
