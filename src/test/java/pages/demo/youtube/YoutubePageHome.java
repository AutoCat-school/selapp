package pages.demo.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.report.Report;

public class YoutubePageHome extends YoutubePage {

    private By byKeywordInput = this.byDynamic("input", "id", "search");
    private By bySearchButton = this.byDynamic("button", "id", "search-icon-legacy");
    private By byChannelTitle = this.byDynamic("ytd-channel-name", "id", "channel-title");

    public YoutubePageHome(WebDriver driver) {
        super(driver);
    }

    public void search(String keyword) {
        Report.info("Search: " + keyword);
        WebElement searchBox = this.findVisibleElement(this.byKeywordInput);
        searchBox.sendKeys(keyword);

        WebElement searchButton = this.findVisibleElement(this.bySearchButton);
        searchButton.click();
    }

    public void hasChannel(String keyword) {
        Report.info("hasChannel: " + keyword);
        WebElement channelTitle = this.findVisibleElement(this.byChannelTitle);
        String title = channelTitle.getText();
        this.verify.contains(title, keyword);
    }
}
