package features.demo.google;

import org.testng.annotations.BeforeClass;

import core.utilities.Utils;
import features.demo.DemoBaseTest;
import pages.demo.google.GooglePage;

public abstract class GoogleBaseTest extends DemoBaseTest {

    protected GooglePage page;

    @BeforeClass
    public void setUp() {
        Utils.println("====> GoogleBaseTest setUp ====");
        super.setUp();

        this.page = this.pageGenerator.getPage(GooglePage.class);
    }
}
