package core.abstractpage;

import org.openqa.selenium.WebDriver;

public abstract class AbstractWeb extends AbstractDriverIO {

    public AbstractWeb(WebDriver driver) {
        super(driver);
    }

    public abstract String getRootUrl();

    public void navigateToTheUrl(String url) {
        this.getDriver().navigate().to(url);
    }

    public String getCurrentUrl() {
        return this.getDriver().getCurrentUrl();
    }
}