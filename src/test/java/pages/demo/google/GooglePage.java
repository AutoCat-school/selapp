package pages.demo.google;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.abstractpage.AbstractWeb;

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
}
