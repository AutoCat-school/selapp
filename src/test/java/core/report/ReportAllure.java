package core.report;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

public class ReportAllure {
    private static AllureLifecycle lifecycle;

    public static synchronized void createTest() {
        lifecycle = Allure.getLifecycle();
    }

    public static void info(String info) {
        step(info, Status.PASSED);
    }

    public static void pass(String info) {
        step(info, Status.PASSED);
    }

    public static void fail(String info) {
        step(info, Status.FAILED);
    }

    public static void skip(String info) {
        step(info, Status.SKIPPED);
    }

    public static void step(String info, Status status) {
        String uuid = UUID.randomUUID().toString();
        lifecycle.startStep(uuid, (new StepResult()).setName(info).setStatus(status));
        lifecycle.stopStep(uuid);
    }

    public static void screenshot(WebDriver driver) {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
    }
}
