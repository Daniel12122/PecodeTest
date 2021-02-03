package pojo.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String address;
    private String suburb;
    private int postalCode;
    private String longitude;
    private String latitude;
    private int placeId;
    private int globalCode;
    private int compoundCode;
}
