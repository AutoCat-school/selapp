package features.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import core.driver.DriverManager;

public abstract class DemoBaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("====> DemoBaseTest setUp ===========");

        this.driver = DriverManager.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        // this.driverManager.quitAllDrivers();
        DriverManager.quitDriver(this.driver);

        System.out.println("<== DemoBaseTest tearDown =======");
    }
}
