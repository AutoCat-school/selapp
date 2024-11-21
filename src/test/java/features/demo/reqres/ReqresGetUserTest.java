package features.demo.reqres;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import core.basetest.ApiBaseTest;
import core.utilities.Utils;
import pages.demo.reqres.ReqresApiLogin;

public class ReqresGetUserTest extends ApiBaseTest {
    ReqresApiLogin loginApi;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        Utils.println("====> ReqresGetUserTest beforeClass ====");
        this.loginApi = this.apiGenerator.getApi(ReqresApiLogin.class);
    }

    @Test
    public void testGetUserData() {
        this.loginApi.callLoginApi("george.bluth@reqres.in", "cityslicka");
        this.loginApi.saveTokenFromResponse();
        this.loginApi.verifyStatus(200);
    }
}
