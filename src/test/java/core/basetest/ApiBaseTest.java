package core.basetest;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import core.page.ApiGenerator;
import core.report.Report;
import core.utilities.Utils;
import io.qameta.allure.testng.AllureTestNg;

@Listeners({
        core.listener.BaseListener.class,
        AllureTestNg.class
})
public abstract class ApiBaseTest {
    protected ApiGenerator apiGenerator;

    @BeforeClass
    public void beforeClass() {
        Utils.println("==> ApiBaseTest beforeClass ===========");
        this.apiGenerator = new ApiGenerator();
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            Report.TestPass(result);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            Report.TestFail(result);
        }
    }
}
