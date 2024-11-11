package features.demo.google;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(core.listener.TestListener.class)
public class GooglePageTest extends GoogleBaseTest {

    @Test
    public void testGoogleSearch() {
        this.page.goToHomePage();

        String title = this.page.getTitle();
        this.page.println("Page title is: " + title);

        // this.page.sleepInSecond(3);
    }

    @Test
    public void testGoogleSearchAnime() {
        this.page.goToHomePage();

        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys("Anime");

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        // this.page.println("GooglePageTest search Anime");

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

        this.page.verifyResultsHas(keyword);

        // this.page.sleepInSecond(1);
    }
}
