package features.demo.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.driver.DriverManager;
import core.utilities.Utils;

public class GoogleSearchTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("GoogleSearchTest setUp");

        DriverManager manager = new DriverManager();
        this.driver = manager.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
        System.out.println("GoogleSearchTest tearDown");
    }

    @Test
    public void testGoogleSearch() {
        this.driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
        Utils.sleep(3000);
    }

    @Test
    public void testGoogleSearchAnime() {
        this.driver.get("https://www.google.com");
        By locator = By.name("q");
        WebElement searchBox = driver.findElement(locator);
        searchBox.sendKeys("Anime");
        searchBox.sendKeys(Keys.RETURN);
        System.out.println("GoogleSearchTest search Anime");
        Utils.sleep(3000);
    }

}
