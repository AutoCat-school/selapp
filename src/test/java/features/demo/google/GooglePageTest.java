package features.demo.google;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import core.report.Report;

public class GooglePageTest extends GoogleBaseTest {

    @Test
    public void testGoogleSearch() {
        Report.createTest("Google Search");
        this.page.goToHomePage();

        String title = this.page.getTitle();
        this.page.println("Page title is: " + title);
        Report.info("Page title is: " + title);

        // this.page.sleepInSecond(3);
    }

    @Test
    public void testGoogleSearchAnime() {
        Report.createTest("Search Anime");

        this.page.goToHomePage();

        WebElement searchBox = this.page.getSearchBox();
        searchBox.sendKeys("Anime");

        WebElement searchButton = this.page.getSearchButton();
        searchButton.click();

        this.page.println("GooglePageTest search Anime");
        Report.pass("Search success");

        // this.page.sleepInSecond(3);
    }

    @Test
    public void testSearchSaiGame() {
        Report.createTest("Search Sai Game");

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
