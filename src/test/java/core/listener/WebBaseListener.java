package core.listener;

import core.page.WebBaseTest;
import core.report.Log;
import core.report.Report;
import core.utilities.Config;
import core.utilities.Utils;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebBaseListener implements ITestListener {
    private static String configName = "listener.report";
    private static String configScreenShotOnPass = "listener.screenshot.on.pass";
    private static String configScreenShotOnFalse = "listener.screenshot.on.false";

    @Override
    public void onStart(ITestContext context) {
        Report.println("====> WebBaseListener onStart");
        Report.setUp();
    }

    @Override
    public void onFinish(ITestContext context) {
        Report.println("<== WebBaseListener onFinish");
        Report.tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        Report.println("--> WebBaseListener onTestStart: " + testName);
        Report.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Report.println("<-- WebBaseListener onTestSuccess");

        if (Config.getBool(WebBaseListener.configName)) {
            Report.pass("Passed: " + getTestName(result));

            if (Config.getBool(WebBaseListener.configScreenShotOnPass)) {
                ITestContext context = result.getTestContext();
                WebDriver driver = (WebDriver) context.getAttribute(WebBaseTest.DefaultDriver);
                Report.screenshot(driver, Log.PASS);
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Report.println("<-- WebBaseListener onTestFailure");

        String format = "Fail: %s";
        String message = String.format(format, result.getThrowable());

        if (Config.getBool(WebBaseListener.configName)) {
            Report.fail(message);

            if (Config.getBool(WebBaseListener.configScreenShotOnFalse)) {
                ITestContext context = result.getTestContext();
                WebDriver driver = (WebDriver) context.getAttribute(WebBaseTest.DefaultDriver);
                Report.screenshot(driver, Log.FAIL);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.println("-- WebBaseListener onTestSkipped");
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }

    protected String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName().replace("test", "");
        testName = Utils.camelToSentence(testName);
        return testName;
    }
}
