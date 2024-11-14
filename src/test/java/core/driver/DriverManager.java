package core.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import core.report.Report;
import core.utilities.Config;
import core.utilities.Utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    protected boolean isRemoteTesting = false;
    protected String remoteWebUrl;
    private ThreadLocal<Map<String, WebDriver>> drivers = ThreadLocal.withInitial(HashMap::new);

    public DriverManager() {
        this.isRemoteTesting = DriverManager.isRemoteTesting();
        this.remoteWebUrl = DriverManager.getRemoteUrl();
    }

    public static String getRemoteUrl() {
        return Config.get("use.remote.web.url");
    }

    public static boolean isRemoteTesting() {
        return Config.getBool("use.remote.web.driver");
    }

    public void setDriver(String key, DriverType driverType) {
        Map<String, WebDriver> driverMap = drivers.get();
        WebDriver driver;
        if (!driverMap.containsKey(key)) {
            switch (driverType) {
                case CHROME:
                    driver = this.getChromeDriver();
                    driverMap.put(key, driver);
                    break;
                case FIREFOX:
                    driver = this.getFirefoxDriver();
                    driverMap.put(key, driver);
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

    protected WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (this.isRemoteTesting) {
            return this.getRemoveWebDriver(options);
        }
        return new ChromeDriver(options);
    }

    protected WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (this.isRemoteTesting) {
            return this.getRemoveWebDriver(options);
        }
        return new FirefoxDriver(options);
    }

    protected WebDriver getRemoveWebDriver(MutableCapabilities options) {
        URI uri;
        URL url;

        try {
            uri = new URI(this.remoteWebUrl + "/wd/hub/");
            url = uri.toURL();
            return new RemoteWebDriver(url, options);
        } catch (URISyntaxException e) {
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String getThreadKey(String key) {
        long threadId = Thread.currentThread().threadId();
        return String.format("thread-%d-%s", threadId, key);
    }

    public WebDriver getWebDriver() {
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

    public WebDriver getDriver(String key) {
        return drivers.get().get(key);
    }

    public void quitAllDrivers() {
        Report.println("#quitAllDrivers");
        Map<String, WebDriver> driverMap = drivers.get();
        for (WebDriver driver : driverMap.values()) {
            if (driver != null) {
                driver.quit();
            }
        }
        driverMap.clear();
    }

    public void quitDriver(String key) {
        Report.println("#quitDriver: " + key);

        Map<String, WebDriver> driverMap = drivers.get();
        WebDriver driver = driverMap.get(key);
        if (driver != null) {
            driver.quit();
            driverMap.remove(key);
        }
    }

    public void quitDriver(WebDriver driver) {
        if (driver == null) {
            return;
        }

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
