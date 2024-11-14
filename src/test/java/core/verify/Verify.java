package core.verify;

import java.util.regex.Matcher;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import core.report.Log;
import core.report.Report;
import core.utilities.Config;
import java.util.regex.Pattern;

public class Verify {

	protected WebDriver driver;

	private String configReportResult = "verify.result.in.report";
	private String configReportIsTrue = "verify.report.is.true";
	private String configTakeScreenShotPass = "verify.screenshot.on.pass";
	private String configTakeScreenShotFalse = "verify.screenshot.on.false";

	protected boolean screenshotOnPass = false;
	protected boolean screenshotOnFalse = false;
	protected boolean reportVerifyResult = false;
	protected boolean reportVerifyIsTrue = false;

	public Verify(WebDriver driver) {
		this.driver = driver;

		this.reportVerifyResult = Config.getBool(this.configReportResult);
		this.reportVerifyIsTrue = Config.getBool(this.configReportIsTrue);
		this.screenshotOnPass = Config.getBool(this.configTakeScreenShotPass);
		this.screenshotOnFalse = this.isScreenshotOnFalse();
	}

	public boolean isScreenshotOnFalse() {
		return Config.getBool(this.configTakeScreenShotFalse);
	}

	public void equals(Object actual, Object expected) {
		this.equals(actual, expected, null);
	}

	public void equals(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			if (this.reportVerifyResult) {
				String pattern = "Verify passed, [%s] equals [%s]";
				Report.pass(String.format(pattern, actual, expected));

				if (this.screenshotOnPass) {
					Report.screenshot(driver, Log.PASS);
				}
			}
		} catch (AssertionError e) {
			if (this.reportVerifyResult) {
				Report.fail("Verify equals fail, " + e.getMessage());

				if (this.screenshotOnFalse) {
					Report.screenshot(driver, Log.FAIL);
				}
			}
			throw e;
		}
	}

	public void contains(String actual, String expected) {
		this.contains(actual, expected, null);
	}

	public void contains(String actual, String expected, String message) {

		try {
			Pattern pattern = Pattern.compile(Pattern.quote(expected));
			Matcher matcher = pattern.matcher(actual);

			Assert.assertTrue(matcher.find(), message);

			if (this.reportVerifyResult) {
				String passPattern = "Verify passed, [%s] contains [%s]";
				Report.pass(String.format(passPattern, actual, expected));

				if (this.screenshotOnPass) {
					Report.screenshot(this.driver, Log.PASS);
				}
			}
		} catch (AssertionError e) {
			String failPattern = "Verify fail, [%s] not contains [%s]";
			String errorMessage = String.format(failPattern, actual, expected);
			if (this.reportVerifyResult) {
				Report.fail(errorMessage);

				if (this.screenshotOnFalse) {
					Report.screenshot(this.driver, Log.FAIL);
				}
			}
			throw new AssertionError(errorMessage);
		}
	}

	public void isTrue(boolean condition) {
		this.isTrue(condition, null);
	}

	public void isTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
			if (this.reportVerifyResult && this.reportVerifyIsTrue) {
				Report.pass("Verify isTrue passed");

				if (this.screenshotOnPass) {
					Report.screenshot(driver, Log.PASS);
				}
			}
		} catch (AssertionError e) {
			if (this.reportVerifyResult) {
				Report.fail("Verify isTrue fail: " + e.getMessage());

				if (this.screenshotOnFalse) {
					Report.screenshot(driver, Log.FAIL);
				}
			}
			throw e;
		}
	}

	public void notTrue(boolean condition) {
		this.notTrue(condition, null);
	}

	public void notTrue(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
			if (Config.getBool(this.configReportResult) && this.reportVerifyIsTrue) {
				Report.pass("Verify notTrue passed");

				if (this.screenshotOnPass) {
					Report.screenshot(driver, Log.PASS);
				}
			}
		} catch (AssertionError e) {
			if (Config.getBool(this.configReportResult)) {
				Report.fail("Verify notTrue fail " + e.getMessage());

				if (this.screenshotOnFalse) {
					Report.screenshot(driver, Log.FAIL);
				}
			}
			throw e;
		}
	}
}
