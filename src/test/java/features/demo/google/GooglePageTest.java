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
import pages.demo.google.GooglePage;

public class GooglePageTest {

    protected WebDriver driver;
    protected GooglePage page;

    @BeforeClass
    public void setUp() {
        System.out.println("GooglePageTest setUp");

        DriverManager manager = new DriverManager();
        this.driver = manager.getWebDriver();

        this.page = new GooglePage(this.driver);
    }

    @AfterClass
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
        System.out.println("GooglePageTest tearDown");
    }

    @Test
    public void testGoogleSearch() {
        this.page.goToHomePage();

        String title = this.page.getTitle();
        System.out.println("Page title is: " + title);
        Utils.sleep(3000);
    }

    @Test
    public void testGoogleSearchAnime() {
        this.page.goToHomePage();

        By locator = By.name("q");
        WebElement searchBox = driver.findElement(locator);
        searchBox.sendKeys("Anime");
        searchBox.sendKeys(Keys.RETURN);
        System.out.println("GoogleSearchTest search Anime");
        Utils.sleep(3000);
    }
}
