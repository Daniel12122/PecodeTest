package utils.controller;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.Admin;
import pojo.companyResponse.CompanyResponse;
import pojo.companyResponse.Result;
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

    private RequestSpecification returnDefaultGetSetup(String token, Admin admin) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", token)
                .param("username", admin.getEmail())
                .param("password", admin.getPassword())
                .param("grant_type", "password")
                .param("scope", "api")
                .urlEncodingEnabled(true);
    }

    public CompanyResponse getCompaniesByNumberPage(String token, Admin admin, int numberPage, int sizePage) {
        return returnDefaultGetSetup(token, admin)
                .when()
                .get(COMPANY_URL + "?page=" + numberPage + "&size=" + sizePage)
                .then()
                .statusCode(200)
                .extract()
                .response().body().as(CompanyResponse.class);
    }

    public Organization getCompanyByID(String token, int id, Admin admin) {
        return returnDefaultGetSetup(token, admin)
                .when()
                .get(COMPANY_URL + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().as(Organization.class);
    }

    public int deleteCompanyByID(String token, int id, Admin admin) {
        return returnDefaultGetSetup(token, admin)
                .when()
                .delete(COMPANY_URL + "/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().getStatusCode();
    }

    private CompanyResponse getCompanyResponseByPageNumber(String token, Admin admin, int numberPage, int sizePage) {
        return getCompaniesByNumberPage(token, admin, numberPage, sizePage);
    }

    private boolean companyExistOnPage(Organization organization, CompanyResponse companyResponse) {
        return companyResponse.getResults()
                .stream().anyMatch(i -> i.getCompanyName().contains(organization.getCompanyName()));
    }

    private Result getCompanyByCompanyName(CompanyResponse companyResponse, Organization organization) {
        return companyResponse.getResults().stream()
                .filter(i -> i.getCompanyName().contains(organization.getCompanyName()))
                .findFirst().orElseThrow(() -> new NoSuchElementException("Could not found company by name - " + organization.getCompanyName()));
    }

    public int getCompanyID(String token, Admin admin, Organization organization) {
        boolean hasNextPage = true;
        for (int i = 1; hasNextPage; i++) {
            CompanyResponse companyResponsePage = getCompanyResponseByPageNumber(token, admin, i, 25);
            hasNextPage = companyResponsePage.isHasNextPage();
            if (companyExistOnPage(organization, companyResponsePage)) {
                return getCompanyByCompanyName(companyResponsePage, organization).getId();
            }
        }
        throw new NoSuchElementException("Could not found company by name - " + organization.getCompanyName());
    }
}
