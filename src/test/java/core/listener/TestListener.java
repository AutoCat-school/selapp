package core.listener;

import core.report.Report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Report.createTest(testName);
        Report.info("Start: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Report.pass("Success: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Report.fail("Failure: " + result.getMethod().getMethodName()
                + " - " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        Report.setUp();
    }

    @Override
    public void onFinish(ITestContext context) {
        Report.tearDown();
    }
}
