package pojo.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.companyResponse.StatusReference;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    private int id;
    private String tradingName;
    private String abn;
    private String acn;
    private String companyName;
    private String taxFileNumber;
    private int statusReferenceId;
    private StatusReference statusReference;
    private String phone;
    private String fax;
    private String incorporated;
    private int stateId;
    private State state;
    private String adminEmail;
    private String parentCompany;
    private String website;
    private String companyType;
    private Address address;
    private PostalAddress postalAddress;

}
