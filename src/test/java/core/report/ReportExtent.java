package core.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.utilities.Config;

public class ReportExtent {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static synchronized ExtentReports getExtentReports() {
        if (ReportExtent.extent == null) {
            ReportExtent.extent = new ExtentReports();
            String reportPath = Config.get("extent.report.path");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            if (Config.getBool("extent.report.theme.dark")) {
                sparkReporter.config().setTheme(Theme.DARK);
            }
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

    public static void skip(String info) {
        info = String.format("<span class='text-warning'>%s</span>", info);
        ReportExtent.getTest().skip(info);
    }
}
