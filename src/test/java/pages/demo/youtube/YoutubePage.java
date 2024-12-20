package pages.demo.youtube;

import org.openqa.selenium.WebDriver;

import core.page.AbstractWeb;

public abstract class YoutubePage extends AbstractWeb {

    public YoutubePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getRootUrl() {
        return "https://www.youtube.com";
    }
}
