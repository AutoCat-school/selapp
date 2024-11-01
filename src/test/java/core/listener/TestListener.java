package core.listener;

import core.report.Report;
import core.utilities.Config;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

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
        String testName = result.getMethod().getMethodName();
        Report.createTest(testName);
        Report.info("Start: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (Config.getBool("listener.report.pass")) {
            Report.pass("Success: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String format = "Failure: %s - %s";
        String message = String.format(format, result.getMethod().getMethodName(), result.getThrowable());
        if (Config.getBool("listener.report.fail")) {
            Report.fail(message);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }
}
