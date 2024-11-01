package core.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportExtent {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static synchronized ExtentReports getExtentReports() {
        if (ReportExtent.extent == null) {
            ReportExtent.extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/report/ExtentReport.html");
            ReportExtent.extent.attachReporter(sparkReporter);
        }
        return ReportExtent.extent;
    }

    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest test = getExtentReports().createTest(testName);
        ReportExtent.testThread.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return ReportExtent.testThread.get();
    }

    public static synchronized void flushReports() {
        ReportExtent.extent.flush();
    }

    public static void info(String info) {
        ReportExtent.getTest().info(info);
    }

    public static void pass(String info) {
        info = String.format("<span class='font-weight-bold' style='color:#9ccc65'>%s</span>", info);
        ReportExtent.getTest().pass(info);
    }

    public static void fail(String info) {
        info = String.format("<span class='text-danger font-weight-bold'>%s</span>", info);
        ReportExtent.getTest().fail(info);
    }
}