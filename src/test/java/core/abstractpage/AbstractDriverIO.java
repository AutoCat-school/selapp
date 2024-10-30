package core.abstractpage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractDriverIO extends AbstractBase {
    protected WebDriver driver;
    protected WebDriverWait explicitDriverWait;

    public AbstractDriverIO(WebDriver driver) {
        this.driver = driver;
        this.explicitDriverWait = new WebDriverWait(this.driver, java.time.Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public By byDynamic(String tagName, String attribute, String value) {
        String path = String.format("//%s[contains(@%s,'%s')]", tagName, attribute, value);
        return By.xpath(path);
    }

    public WebElement findVisibleElement(By elementBy) throws NoSuchElementException {
        try {
            return this.explicitDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
        } catch (TimeoutException e) {
            throw new NoSuchElementException(String.format("Element not found: %s", elementBy.toString()), e);
        }
    }

    public List<WebElement> findElements(By elementBy) throws NoSuchElementException {
        try {
            return this.explicitDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
        } catch (TimeoutException e) {
            throw new NoSuchElementException(String.format("Elements not found: %s", elementBy.toString()), e);
        }
    }
}