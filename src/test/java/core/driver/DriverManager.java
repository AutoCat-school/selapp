package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    protected WebDriver webDriver;

    public WebDriver getWebDriver() {
        if (this.webDriver == null) {
            this.webDriver = new ChromeDriver();
        }
        return this.webDriver;
    }
}
