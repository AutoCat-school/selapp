package core.listener;

import core.report.Report;
import core.utilities.Utils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BaseListener implements ITestListener {
    public static String configName = "listener.report";
    public static String configScreenShotOnPass = "listener.screenshot.on.pass";
    public static String configScreenShotOnFalse = "listener.screenshot.on.false";

    @Override
    public void onStart(ITestContext context) {
        Report.println("====> BaseListener onStart");
        Report.setUp();
    }

    @Override
    public void onFinish(ITestContext context) {
        Report.println("<== BaseListener onFinish");
        Report.tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = Utils.getTestName(result);
        Report.println("--> BaseListener onTestStart: " + testName);
        Report.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Report.println("<-- BaseListener onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Report.println("<-- BaseListener onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.println("-- BaseListener onTestSkipped");
        Report.skip("Skipped: " + result.getMethod().getMethodName());
    }
}
