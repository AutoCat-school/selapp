package core.report;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import core.basetest.WebBaseTest;
import core.listener.BaseListener;
import core.utilities.Config;
import core.utilities.Utils;

public class Report {

    public static void TestPass(ITestResult result) {
        if (Config.getBool(BaseListener.configName)) {
            Report.pass("Passed: " + getTestName(result));

            if (Config.getBool(BaseListener.configScreenShotOnPass)) {
                ITestContext context = result.getTestContext();
                WebDriver driver = (WebDriver) context.getAttribute(WebBaseTest.DefaultDriver);
                Report.screenshot(driver, Log.PASS);
            }
        }
    }

    public static void TestFail(ITestResult result) {
        String format = "Fail: %s";
        String message = String.format(format, result.getThrowable());

        if (Config.getBool(BaseListener.configName)) {
            Report.fail(message);

            if (Config.getBool(BaseListener.configScreenShotOnFalse)) {
                ITestContext context = result.getTestContext();
                WebDriver driver = (WebDriver) context.getAttribute(WebBaseTest.DefaultDriver);
                Report.screenshot(driver, Log.FAIL);
            }
        }
    }

    public static void println(String log) {
        Utils.println(log);
    }

    public static void printError(String log) {
        Utils.printError(log);
    }

    public static void setUp() {
        ReportExtent.getExtentReports();
    }

    public static void tearDown() {
        ReportExtent.flushReports();
    }

    public static void createTest(String testName) {
        ReportExtent.createTest(testName);
        ReportAllure.createTest();
    }

    public static void info(String info) {
        ReportExtent.info(info);
        ReportAllure.info(info);
    }

    public static void pass(String info) {
        ReportExtent.pass(info);
        ReportAllure.pass(info);
    }

    public static void fail(String info) {
        ReportExtent.fail(info);
        ReportAllure.fail(info);
    }

    public static void skip(String info) {
        ReportExtent.skip(info);
        ReportAllure.skip(info);
    }

    public static void screenshot(WebDriver driver, Log log) {
        ReportExtent.screenshot(driver, log);
        ReportAllure.screenshot(driver);
    }

    protected static String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName().replace("test", "");
        testName = Utils.camelToSentence(testName);
        return testName;
    }
}
