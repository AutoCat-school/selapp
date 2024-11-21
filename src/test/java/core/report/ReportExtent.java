package core.report;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import core.utilities.Config;
import core.utilities.Utils;

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
        ExtentTest testCase = ReportExtent.testThread.get();
        if (testCase == null) {
            testCase = getExtentReports().createTest("DummyTestCase");
            String warning = "Extent report was not created. Please ensure your test class is executed with a Listener.";
            Utils.printWarning(warning);
        }
        return testCase;
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

    public static void screenshot(WebDriver driver, Log log) {
        ExtentTest extentTest = ReportExtent.getTest();

        if (extentTest == null || driver == null) {
            return;
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        String source = "data:image/png;base64," + ts.getScreenshotAs(OutputType.BASE64);
        String html = "";
        html += "<a href='" + source + "' data-featherlight='image'>";
        html += "<img style='width: 70%' src='" + source + "'/>";
        html += "</a>";
        extentTest.log(getExtStatus(log), html);
    }

    public static com.aventstack.extentreports.Status getExtStatus(Log status) {
        switch (status) {
            case PASS:
                return com.aventstack.extentreports.Status.PASS;
            case FAIL:
                return com.aventstack.extentreports.Status.FAIL;
            case WARNING:
                return com.aventstack.extentreports.Status.WARNING;
            case INFO:
            default:
                return com.aventstack.extentreports.Status.INFO;
        }
    }
}
