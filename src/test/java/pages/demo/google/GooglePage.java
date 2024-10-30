package pages.demo.google;

import org.openqa.selenium.WebDriver;

import core.abstractpage.AbstractWeb;

public class GooglePage extends AbstractWeb {

    @Override
    public String getRootUrl() {
        return "https://www.google.com";
    }
}
