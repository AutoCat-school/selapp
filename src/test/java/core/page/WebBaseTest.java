package core.page;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import core.driver.DriverManager;
import core.report.Report;
import core.utilities.Utils;

@Listeners(core.listener.WebBaseListener.class)
public abstract class WebBaseTest {

    public static String DefaultDriver = "DefaultDriver";

    protected WebDriver driver;
    protected DriverManager driverManager;
    protected PageGenerator pageGenerator;

    @BeforeSuite
    public void beforeSuite() {
        if (!Utils.isSeleniumGridOnline()) {
            String message = "Selenium Grid is offline. Please start the Selenium Grid.";
            Report.printError(message);
            throw new RuntimeException(message);
        }
    }

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

    @BeforeMethod
    public void setup(ITestContext context) {
        context.setAttribute(WebBaseTest.DefaultDriver, this.driver);
    }
}
