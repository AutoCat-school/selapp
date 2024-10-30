package core.abstractpage;

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
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebElement findVisibleElement(By elementBy) throws NoSuchElementException {
        try {
            return explicitDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
        } catch (TimeoutException e) {
            throw new NoSuchElementException(String.format("Element not found: %s", elementBy.toString()), e);
        }
    }

}