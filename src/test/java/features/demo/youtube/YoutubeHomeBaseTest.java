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
        this.page = this.pageGenerator.getPage(YoutubePageHome.class);
    }

    @Test
    public void testYoutubeSearch() {
        this.page.goToHomePage();
        this.page.search("Sai Game");
        // this.page.sleepInSecond(3);
    }
}
