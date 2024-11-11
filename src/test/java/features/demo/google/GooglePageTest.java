package features.demo.google;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class GooglePageTest extends GoogleBaseTest {

    @Test
    public void testGooglePageSearch() {
        this.page.goToHomePage();

        String title = this.page.getTitle();
        this.page.println("Page title is: " + title);

        // this.page.sleepInSecond(3);
    }

    @Test
    public void testGooglePageSearchAnime() {
        this.page.goToHomePage();

        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys("Anime");

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        // this.page.println("GooglePageTest search Anime");
        // this.page.sleepInSecond(3);
    }

    @Test
    public void testPageSearchSaiGame() {
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
