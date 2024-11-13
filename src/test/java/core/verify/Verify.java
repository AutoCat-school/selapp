package core.verify;

import org.testng.Assert;

import core.report.Report;
import core.utilities.Config;

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
			Assert.assertTrue(actual.contains(expected), message);
			if (Config.getBool(Verify.configName)) {
				String pattern = "Verify passed, [%s] contains [%s]";
				Report.pass(String.format(pattern, actual, expected));
			}
		} catch (AssertionError e) {
			if (Config.getBool(Verify.configName)) {
				String pattern = "Verify fail, [%s] not contains [%s]";
				Report.fail(String.format(pattern, actual, expected));
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
