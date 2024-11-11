package features.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import core.driver.DriverManager;
import core.page.PageGenerator;
import core.utilities.Utils;

@Listeners(core.listener.TestListener.class)
public abstract class DemoBaseTest {

    protected WebDriver driver;
    protected DriverManager manager;
    protected PageGenerator pageGenerator;

    @BeforeClass
    public void setUp() {
        Utils.println("====> DemoBaseTest setUp ===========");

        this.manager = new DriverManager();
        this.driver = manager.getWebDriver();

        this.pageGenerator = new PageGenerator(this.driver);
    }

    @AfterClass
    public void tearDown() {
        this.manager.quitAllDrivers();
        // this.manager.quitDriver(this.driver);

        Utils.println("<== DemoBaseTest tearDown =======");
    }
}
