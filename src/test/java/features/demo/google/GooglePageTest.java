package features.demo.google;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import core.driver.DriverManager;
import core.verify.Verify;
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
        // this.page.sleepInSecond(3);
    }

    @Test
    public void testGoogleSearchAnime() {
        this.page.goToHomePage();

        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys("Anime");

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        System.out.println("GooglePageTest search Anime");
        // this.page.sleepInSecond(3);
    }

    @Test
    public void testSearchSaiGame() {
        this.page.goToHomePage();

        String keyword = "Sai Game";
        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys(keyword);

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        System.out.println("GooglePageTest search Anime");
        this.page.sleepInSecond(3);
    }
}
