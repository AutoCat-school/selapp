package features.demo.google;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.driver.DriverManager;
import features.demo.DemoBaseTest;
import pages.demo.google.GooglePage;

public class GooglePageTwoTest extends DemoBaseTest {

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
    public void testGoogleSearchTwo() {
        this.page.goToHomePage();

        String title = this.page.getTitle();
        this.page.println("Page title is: " + title);
        // this.page.sleepInSecond(3);
    }

    @Test
    public void testGoogleSearchAnimeTwo() {
        this.page.goToHomePage();

        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys("Anime");

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        this.page.println("GooglePageTest search Anime");
        // this.page.sleepInSecond(3);
    }

    @Test
    public void testSearchSaiGameTwo() {
        this.page.goToHomePage();

        String keyword = "Sai Game";
        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys(keyword);

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        this.page.verifyResultsHas(keyword);
        // this.page.sleepInSecond(1);
    }
}
