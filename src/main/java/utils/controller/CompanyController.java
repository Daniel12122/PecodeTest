package utils.controller;

import io.restassured.http.ContentType;
import pojo.Admin;
import pojo.companyResponse.CompanyResponse;
import pojo.organization.Organization;

import java.util.NoSuchElementException;

import static io.restassured.RestAssured.given;
import static utils.Constant.COMPANY_URL;


public class CompanyController {
    public void postCompany(Organization organization, String token) {
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", token)
                .body(organization)
                .when()
                .post(COMPANY_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public CompanyResponse getCompaniesByNumberPage(String token, Admin admin, int numberPage, int sizePage) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", token)
                .param("username", admin.getEmail())
                .param("password", admin.getPassword())
                .param("grant_type", "password")
                .param("scope", "api")
                .urlEncodingEnabled(true)
                .when()
                .get(COMPANY_URL + "?page=" + numberPage + "&size=" + sizePage)
                .then()
                .statusCode(200)
                .extract()
                .response().body().as(CompanyResponse.class);
    }

    public Organization getCompanyByID(String token, int id, Admin admin) {
        return given()
                .header("Content-Type ", "application/x-www-form-urlencoded")
                .header("Authorization", token)
                .param("username", admin.getEmail())
                .param("password", admin.getPassword())
                .param("grant_type", "password")
                .param("scope", "api")
                .urlEncodingEnabled(true)
                .when()
                .get(COMPANY_URL + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().as(Organization.class);
    }

    public int deleteCompanyByID(String token, int id, Admin admin) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", token)
                .param("username", admin.getEmail())
                .param("password", admin.getPassword())
                .param("grant_type", "password")
                .param("scope", "api")
                .urlEncodingEnabled(true)
                .when()
                .delete(COMPANY_URL + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().getStatusCode();
    }

    public CompanyResponse getCompanyResponseByPageNumber(String token, Admin admin, int numberPage, int sizePage) {
        return getCompaniesByNumberPage(token, admin, numberPage, sizePage);
    }

    public boolean searchCompanyOnPage(Organization organization, CompanyResponse companyResponse) {
        return companyResponse.getResults()
                .stream().anyMatch(i -> i.getCompanyName().contains(organization.getCompanyName()));
    }

    public int getCompanyThatFinding(CompanyResponse companyResponse, Organization organization) {
        return companyResponse.getResults().stream()
                .filter(i -> i.getCompanyName().contains(organization.getCompanyName()))
                .findFirst().get().getId();

    }

    public int getCompanyID(String token, Admin admin, Organization organization) {
        int id = 0;
        boolean hasNextPage = true;
        for (int i = 1; hasNextPage; i++) {
            CompanyResponse companyResponsePage = getCompanyResponseByPageNumber(token, admin, i, 25);
            boolean companyOnPage = searchCompanyOnPage(organization, companyResponsePage);
            if (companyOnPage) {
                id = getCompanyThatFinding(companyResponsePage, organization);
                return id;
            }
        }
        throw new NoSuchElementException("Нихуя нема");
    }
}
