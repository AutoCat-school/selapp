package features.demo.google;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

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

        this.page.println("GooglePageTest search Anime");
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

        // List<WebElement> elements = this.page.getSearchResults();
        // WebElement firstElement = elements.get(0);
        // String textResult = firstElement.getText();
        // Verify.equals(textResult, keyword);
        // this.page.println("firstElement: " + firstElement.getText());

        // this.page.sleepInSecond(1);
    }
}
