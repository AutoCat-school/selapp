package core.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractWeb extends AbstractDriverIO {

    public AbstractWeb(WebDriver driver) {
        super(driver);
    }

    public abstract String getRootUrl();

    public void goTo(String url) {
        this.getDriver().navigate().to(url);
    }

    public void goToHomePage() {
        this.goTo(this.getRootUrl());
    }

    public String getCurrentUrl() {
        return this.getDriver().getCurrentUrl();
    }

    public String getTitle() {
        return this.getDriver().getTitle();
    }
}