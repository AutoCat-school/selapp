package core.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties;

    static {
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(Config.get(key));
    }

    public static boolean getBool(String key) {
        return Boolean.parseBoolean(Config.get(key));
    }
}
