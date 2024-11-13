package pages.demo.google;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.page.AbstractWeb;

public class GooglePage extends AbstractWeb {

    private By byKeywordInput = this.byDynamic("textarea", "name", "q");
    private By bySearchButton = this.byDynamic("input", "type", "submit");
    private By bySearchResults = this.byDynamic("h3", "class", "LC20lb");

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getRootUrl() {
        return "https://www.google.com?hl=en";
    }

    public WebElement getSearchBox() {
        return this.findVisibleElement(this.byKeywordInput);
    }

    public WebElement getSearchButton() {
        return this.findVisibleElement(this.bySearchButton);
    }

    public List<WebElement> getSearchResults() {
        return this.findElements(this.bySearchResults);
    }

    public void verifyResultsHas(String keyword) {
        String textResult;
        boolean hasResult = false;
        List<WebElement> elements = this.getSearchResults();
        for (WebElement webElement : elements) {
            textResult = webElement.getText();
            if (textResult.equals(keyword)) {
                hasResult = true;
                break;
            }
        }
        this.verify.isTrue(hasResult, "Keyword not in the result");
    }
}
