package utils;

import pojo.Admin;
import pojo.loginResponce.LoginResponse;

import static io.restassured.RestAssured.given;
import static utils.Constant.LOGIN_URL;

public class LoginUserAPI {
    public LoginResponse loginUserByAPI(Admin admin) {
        return given()
                .header("Content-Type ", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic Y2VyZWx5LXVpOnNlY3JldA==")
                .param("username", admin.getEmail())
                .param("password", admin.getPassword())
                .param("grant_type", "password")
                .param("scope", "api")
                .urlEncodingEnabled(true)
                .when()
                .post(LOGIN_URL)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(LoginResponse.class);
    }

    public String getToken(LoginResponse loginResponse) {
        return loginResponse.getToken_type().substring(0, 1).toUpperCase()
                + loginResponse.getToken_type().substring(1)
                + " "
                + loginResponse.getAccess_token();
    }
}
