package features.demo.google;

import org.testng.annotations.BeforeClass;

import features.demo.DemoBaseTest;
import pages.demo.google.GooglePage;

public abstract class GoogleBaseTest extends DemoBaseTest {

    protected GooglePage page;

    @BeforeClass
    public void setUp() {
        System.out.println("GoogleBaseTest setUp");
        super.setUp();
        this.page = new GooglePage(this.driver);
    }
}
