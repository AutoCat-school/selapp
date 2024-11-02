package core.listener;

import core.report.Report;
import core.utilities.Config;
import core.utilities.Utils;

import org.slf4j.helpers.Util;
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
        String testName = result.getMethod().getMethodName().replace("test", "");
        testName = Utils.camelToSentence(testName);
        Report.createTest(testName);
        Report.info("Start: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (Config.getBool(TestListener.configName)) {
            Report.pass("Passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String format = "Fail: %s - %s";
        String message = String.format(format, result.getMethod().getMethodName(), result.getThrowable());
        if (Config.getBool(TestListener.configName)) {
            Report.fail(message);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }
}
