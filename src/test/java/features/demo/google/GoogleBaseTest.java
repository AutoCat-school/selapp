package features.demo.google;

import org.testng.annotations.BeforeClass;

import core.basetest.WebBaseTest;
import core.utilities.Utils;
import pages.demo.google.GooglePage;

public abstract class GoogleBaseTest extends WebBaseTest {

    protected GooglePage page;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        Utils.println("====> GoogleBaseTest beforeClass ====");

        this.page = this.pageGenerator.getPage(GooglePage.class);
    }
}
