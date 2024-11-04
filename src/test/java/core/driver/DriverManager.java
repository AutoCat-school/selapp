package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import core.report.Report;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static ThreadLocal<Map<String, WebDriver>> drivers = ThreadLocal.withInitial(HashMap::new);

    public static void setDriver(String key, DriverType driverType) {
        Map<String, WebDriver> driverMap = drivers.get();

        if (!driverMap.containsKey(key)) {
            switch (driverType) {
                case CHROME:
                    driverMap.put(key, new ChromeDriver());
                    break;
                case FIREFOX:
                    driverMap.put(key, new FirefoxDriver());
                    break;
                case ANDROID:
                    // TODO: need appium
                    break;
                case IOS:
                    // TODO: need appium
                    break;
                default:
                    throw new IllegalArgumentException("Driver not found: " + driverType);
            }
        }
    }

    public static String getThreadKey(String key) {
        long threadId = Thread.currentThread().threadId();
        return String.format("thread-%d-%s", threadId, key);
    }

    public static WebDriver getWebDriver() {
        String key = getThreadKey("web");
        Report.println("#getWebDriver: " + key);
        WebDriver driver = getDriver(key);
        if (driver == null) {
            Report.println("#setDriver: NEW");
            setDriver(key, DriverType.CHROME);
            driver = getDriver(key);
        }
        return driver;
    }

    public static WebDriver getDriver(String key) {
        return drivers.get().get(key);
    }

    public static void quitAllDrivers() {
        Report.println("#quitAllDrivers");
        Map<String, WebDriver> driverMap = drivers.get();
        for (WebDriver driver : driverMap.values()) {
            if (driver != null) {
                driver.quit();
            }
        }
        driverMap.clear();
    }

    public static void quitDriver(String key) {
        Report.println("#quitDriver: " + key);

        Map<String, WebDriver> driverMap = drivers.get();
        WebDriver driver = driverMap.get(key);
        if (driver != null) {
            driver.quit();
            driverMap.remove(key);
        }
    }

    public static void quitDriver(WebDriver driver) {
        if (driver == null)
            return;

        Report.println("#quitDriver: " + driver);
        String keyToRemove = null;
        Map<String, WebDriver> driverMap = drivers.get();
        for (Map.Entry<String, WebDriver> entry : driverMap.entrySet()) {
            if (entry.getValue().equals(driver)) {
                keyToRemove = entry.getKey();
                break;
            }
        }

        if (keyToRemove != null) {
            driver.quit();
            driverMap.remove(keyToRemove);
        }
    }
}
