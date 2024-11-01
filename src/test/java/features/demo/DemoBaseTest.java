package features.demo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import core.driver.DriverManager;

public abstract class DemoBaseTest {

    protected WebDriver driver;
    protected DriverManager driverManager;

    @BeforeClass
    public void setUp() {
        System.out.println("DemoBaseTest setUp");

        this.driverManager = new DriverManager();
        this.driver = this.driverManager.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

        System.out.println("DemoBaseTest tearDown");
    }
}
