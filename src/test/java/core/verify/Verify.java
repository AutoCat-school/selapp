package core.verify;

import org.testng.Assert;

import core.report.Report;

public class Verify {
	public static void equals(Object actual, Object expected) {
		Verify.equals(actual, expected, null);
	}

	public static void equals(Object actual, Object expected, String message) {

		try {
			Assert.assertEquals(actual, expected, message);
			Report.pass("Verify equals passed");
		} catch (AssertionError e) {
			Report.fail("Verify equals fail " + e.getMessage());
			throw e;
		}
	}

	public static void isTrue(boolean condition) {
		Verify.isTrue(condition, null);
	}

	public static void isTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
			Report.pass("Verify isTrue passed");
		} catch (AssertionError e) {
			Report.fail("Verify isTrue fail " + e.getMessage());
			throw e;
		}
	}

	public static void notTrue(boolean condition) {
		Verify.notTrue(condition, null);
	}

	public static void notTrue(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
			Report.pass("Verify notTrue passed");
		} catch (AssertionError e) {
			Report.fail("Verify notTrue fail " + e.getMessage());
			throw e;
		}
	}
}
