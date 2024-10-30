package pages.demo.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.abstractpage.AbstractWeb;

public class GooglePage extends AbstractWeb {

    protected By byKeywordInput = this.byDynamic("textarea", "name", "q");

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getRootUrl() {
        return "https://www.google.com?hl=en";
    }

    public WebElement getKeywordInput() {
        return this.findVisibleElement(this.byKeywordInput);
    }
}
