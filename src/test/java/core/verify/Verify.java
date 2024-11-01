package core.verify;

import org.testng.Assert;

public class Verify {
	public static void equals(Object actual, Object expected) {
		Verify.equals(actual, expected, null);
	}

	public static void equals(Object actual, Object expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	public static void isTrue(boolean condition) {
		Verify.isTrue(condition, null);
	}

	public static void isTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}

	public static void notTrue(boolean condition) {
		Verify.notTrue(condition, null);
	}

	public static void notTrue(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}
}
