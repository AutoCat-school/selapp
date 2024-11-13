package core.page;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import core.driver.DriverManager;
import core.utilities.Utils;

@Listeners(core.listener.TestListener.class)
public abstract class WebBaseTest {

    protected WebDriver driver;
    protected DriverManager driverManager;
    protected PageGenerator pageGenerator;

    @BeforeClass
    public void setUp() {
        Utils.println("====> WebBase setUp ===========");

        this.driverManager = new DriverManager();
        this.driver = driverManager.getWebDriver();

        this.pageGenerator = new PageGenerator(this.driver);
    }

    @AfterClass
    public void tearDown() {
        this.driverManager.quitAllDrivers();
        // this.manager.quitDriver(this.driver);

        Utils.println("<== WebBase tearDown =======");
    }
}
