package pages.demo.reqres;

public class ReqresApiLogin extends ReqresApi {
    @Override
    protected String getUri() {
        return "/api/login";
    }

    public void callLoginApi(String email, String password) {
        String format = "{ \"email\": \"%s\", \"password\": \"%s\" }";
        String jsonBody = String.format(format, email, password);
        this.postJson(this.getUrl(), jsonBody);
    }

    public void callLoginWithoutPassword(String email) {
        String format = "{ \"email\": \"%s\"}";
        String jsonBody = String.format(format, email);
        this.postJson(this.getUrl(), jsonBody);
    }

    public void callLoginWithoutEmail(String password) {
        String format = "{ \"password\": \"%s\" }";
        String jsonBody = String.format(format, password);
        this.postJson(this.getUrl(), jsonBody);
    }

    public void saveTokenFromResponse() {
        this.setToken(this.getJsonPath("token"));
    }
}
