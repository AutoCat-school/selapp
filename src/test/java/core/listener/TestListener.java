package core.listener;

import core.report.Log;
import core.report.Report;
import core.utilities.Config;
import core.utilities.Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static String configName = "listener.report";

    @Override
    public void onStart(ITestContext context) {
        Report.setUp();
    }

    @Override
    public void onFinish(ITestContext context) {
        Report.tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        Report.createTest(testName);
        Report.println("-- Test Start: " + testName);
        Report.info("Start: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (Config.getBool(TestListener.configName)) {
            Report.pass("Passed: " + getTestName(result));

            if (this.screenshotOnPass) {
                Report.screenshot(driver, Log.PASS);
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String format = "Fail: %s - %s";
        String message = String.format(format, getTestName(result), result.getThrowable());
        if (Config.getBool(TestListener.configName)) {
            Report.fail(message);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }

    protected String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName().replace("test", "");
        testName = Utils.camelToSentence(testName);
        return testName;
    }
}
