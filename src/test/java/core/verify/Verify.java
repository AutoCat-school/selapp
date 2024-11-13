package core.verify;

import java.util.regex.Matcher;
import org.testng.Assert;
import core.report.Report;
import core.utilities.Config;
import java.util.regex.Pattern;

public class Verify {
	private static String configName = "verify.report";
	private static String configIsTrue = "verify.report.is.true";

	public static void equals(Object actual, Object expected) {
		Verify.equals(actual, expected, null);
	}

	public static void equals(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
			if (Config.getBool(Verify.configName)) {
				String pattern = "Verify passed, [%s] equals [%s]";
				Report.pass(String.format(pattern, actual, expected));
			}
		} catch (AssertionError e) {
			if (Config.getBool(Verify.configName)) {
				Report.fail("Verify equals fail, " + e.getMessage());
			}
			throw e;
		}
	}

	public static void contains(String actual, String expected) {
		Verify.contains(actual, expected, null);
	}

	public static void contains(String actual, String expected, String message) {
		try {
			Pattern pattern = Pattern.compile(Pattern.quote(expected));
			Matcher matcher = pattern.matcher(actual);

			Assert.assertTrue(matcher.find(), message);

			if (Config.getBool(Verify.configName)) {
				String passPattern = "Verify passed, [%s] contains [%s]";
				Report.pass(String.format(passPattern, actual, expected));
			}
		} catch (AssertionError e) {
			if (Config.getBool(Verify.configName)) {
				String failPattern = "Verify fail, [%s] not contains [%s]";
				Report.fail(String.format(failPattern, actual, expected));
			}
			throw e;
		}
	}

	public static void isTrue(boolean condition) {
		Verify.isTrue(condition, null);
	}

	public static void isTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
			if (Config.getBool(Verify.configName) && Config.getBool(Verify.configIsTrue)) {
				Report.pass("Verify isTrue passed");
			}
		} catch (AssertionError e) {
			if (Config.getBool(Verify.configName)) {
				Report.fail("Verify isTrue fail: " + e.getMessage());
			}
			throw e;
		}
	}

	public static void notTrue(boolean condition) {
		Verify.notTrue(condition, null);
	}

	public static void notTrue(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
			if (Config.getBool(Verify.configName) && Config.getBool(Verify.configIsTrue)) {
				Report.pass("Verify notTrue passed");
			}
		} catch (AssertionError e) {
			if (Config.getBool(Verify.configName)) {
				Report.fail("Verify notTrue fail " + e.getMessage());
			}
			throw e;
		}
	}
}
