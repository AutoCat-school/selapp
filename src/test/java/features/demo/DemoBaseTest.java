package features.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import core.driver.DriverManager;
import core.driver.DriverType;

public abstract class DemoBaseTest {

    protected WebDriver driver;
    protected WebDriver driverChrome;
    protected WebDriver driverApi;
    protected WebDriver driverAndroid;
    protected WebDriver driverAndroid2;
    protected DriverManager manager;

    @BeforeClass
    public void setUp() {
        System.out.println("====> DemoBaseTest setUp ===========");

        this.manager = new DriverManager();
        this.driver = manager.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        this.manager.quitAllDrivers();
        // this.manager.quitDriver(this.driver);

        System.out.println("<== DemoBaseTest tearDown =======");
    }
}
