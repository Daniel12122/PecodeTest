package test;

import org.testng.annotations.Test;
import pojo.Admin;
import pojo.organization.Organization;
import utils.LoginUserAPI;
import utils.builder.AdminBuilder;
import utils.builder.OrganizationBuilder;
import utils.controller.CompanyController;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateCompanyTest {

    private Organization organizationBodyObj = OrganizationBuilder.getDefaultOrganization();
    private Admin admin = AdminBuilder.getDefaultAdmin();
    private CompanyController companyController = new CompanyController();
    private LoginUserAPI loginUserAPI = new LoginUserAPI();

    @Test
    public void createCompanyTest() {
        String token = loginUserAPI.getToken(loginUserAPI.loginUserByAPI(admin));
        companyController.postCompany(organizationBodyObj, token);
        int id = companyController.getCompanyID(token, admin, organizationBodyObj);
        Organization organization = companyController.getCompanyByID(token,id,admin);
        assertThat(organizationBodyObj.getCompanyName()).isEqualTo(organization.getCompanyName());
        companyController.deleteCompanyByID(token, id, admin);
    }
}
