package core.verify;

import org.testng.Assert;

public class Verify {
	public static void equals(Object actual, Object expected) {
		Verify.equals(actual, expected, null);
	}

	public static void equals(Object actual, Object expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}
}
