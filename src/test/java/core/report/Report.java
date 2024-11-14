package core.report;

import org.openqa.selenium.WebDriver;
import core.utilities.Utils;

public class Report {
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
    }

    public static void info(String info) {
        ReportExtent.info(info);
    }

    public static void pass(String info) {
        ReportExtent.pass(info);
    }

    public static void fail(String info) {
        ReportExtent.fail(info);
    }

    public static void skip(String info) {
        ReportExtent.skip(info);
    }

    public static void screenshot(WebDriver driver, Log log) {
        ReportExtent.screenshot(driver, log);
    }
}
