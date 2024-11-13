package features.demo.youtube;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.utilities.Utils;
import features.demo.DemoBaseTest;
import pages.demo.youtube.YoutubePageHome;

public class YoutubeHomeBaseTest extends DemoBaseTest {

    protected YoutubePageHome page;

    @BeforeClass
    public void setUp() {
        Utils.println("====> GoogleBaseTest setUp ====");
        super.setUp();
        this.page = new YoutubePageHome(this.driver);
    }

    @Test
    public void testYoutubeSearch() {
        String keyword = "Sai Game";

        this.page.goToHomePage();
        this.page.search(keyword);
        this.page.hasChannel(keyword);

        this.page.sleepInSecond(1);
    }

    @Test
    public void testYoutubeSelenium() {
        String keyword = "Selenium";

        this.page.goToHomePage();
        this.page.search(keyword);
        this.page.hasChannel(keyword);

        this.page.sleepInSecond(1);
    }
}
