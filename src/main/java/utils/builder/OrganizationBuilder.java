package utils.builder;

import com.github.javafaker.Faker;
import pojo.companyResponse.StatusReference;
import pojo.organization.Address;
import pojo.organization.Organization;
import pojo.organization.PostalAddress;
import pojo.organization.State;

import java.util.Locale;

public class OrganizationBuilder {
    private static Faker faker = new Faker(Locale.US);

    public static Organization getDefaultOrganization() {
        return Organization.builder()
                .tradingName(faker.name().fullName())
                .abn(faker.phoneNumber().subscriberNumber(10))
                .acn(faker.phoneNumber().subscriberNumber(10))
                .companyName(faker.name().username())
                .taxFileNumber(faker.phoneNumber().subscriberNumber(10))
                .statusReferenceId(defaultStatusReference().getId())
                .phone(faker.phoneNumber().subscriberNumber(10))
                .fax(faker.phoneNumber().subscriberNumber(6))
                .incorporated("Yes")
                .stateId(defaultState().getId())
                .adminEmail("qweqe@gmail.com")
                .parentCompany("Not Applicable")
                .website("www.cerely.com.ua")
                .companyType("Contractor")
                .address(defaultAddress())
                .postalAddress(defaultPostalAddress())
                .build();
    }

    public static Address defaultAddress() {
        return Address.builder()
                .address(faker.address().fullAddress())
                .postalCode(faker.number().randomDigitNotZero())
                .suburb(faker.address().state())
                .build();
    }

    public static PostalAddress defaultPostalAddress() {
        return PostalAddress.builder()
                .address(faker.address().fullAddress())
                .postalCode(faker.number().randomDigitNotZero())
                .suburb(faker.address().state())
                .build();
    }

    public static State defaultState() {
        return State.builder()
                .id(329)
                .country(faker.country().name())
                .state(faker.country().capital())
                .abbreviation(faker.country().capital())
                .enabled(true)
                .build();
    }

    public static StatusReference defaultStatusReference() {
        return StatusReference.builder()
                .id(1)
                .name("Active")
                .build();
    }
}
