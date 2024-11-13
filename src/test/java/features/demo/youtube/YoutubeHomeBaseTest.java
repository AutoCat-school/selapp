package features.demo.youtube;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.page.WebBaseTest;
import core.utilities.Utils;
import pages.demo.youtube.YoutubePageHome;

public class YoutubeHomeBaseTest extends WebBaseTest {

    protected YoutubePageHome page;

    @BeforeClass
    public void setUp() {
        Utils.println("====> GoogleBaseTest setUp ====");
        super.setUp();
        this.page = this.pageGenerator.getPage(YoutubePageHome.class);
    }

    @Test
    public void testYoutubeSearch() {
        String keyword = "Sai Game";

        this.page.goToHomePage();
        this.page.search(keyword);
        this.page.hasChannel(keyword);

        int instanceId = System.identityHashCode(this.page);
        this.page.println("Page ID: " + instanceId);

        // this.page.sleepInSecond(1);
    }

    @Test
    public void testYoutubeSelenium() {
        String keyword = "@AutoCat24";

        YoutubePageHome youtubePage = this.pageGenerator.getPage(YoutubePageHome.class);

        youtubePage.goToHomePage();
        youtubePage.search(keyword);
        youtubePage.hasChannel("Auto Cat");

        int instanceId = System.identityHashCode(youtubePage);
        this.page.println("Page ID: " + instanceId);

        // youtubePage.sleepInSecond(1);
    }
}
