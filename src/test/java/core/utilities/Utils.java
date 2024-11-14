package core.utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import core.driver.DriverManager;

public class Utils {

    public static void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String camelToSentence(String string) {
        String sentence = string.replaceAll("([a-z])([A-Z])", "$1 $2");
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    public static void println(String log) {
        System.out.println(log);
    }

    public static void printWarning(String log) {
        println("\u001B[33mWarning: " + log + "\u001B[0m");
    }

    public static void printError(String log) {
        println("\u001B[31mError: " + log + "\u001B[0m");
    }

    public static boolean isSeleniumGridOnline() {
        if (!DriverManager.isRemoteTesting()) {
            return true;
        }

        try {
            URI uri = new URI(DriverManager.getRemoteUrl() + "/wd/hub/status");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int statusCode = connection.getResponseCode();
            return (statusCode == 200);
        } catch (URISyntaxException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
