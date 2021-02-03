package pojo.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int id;
    private String tradingName;
    private String companyName;
    private String acn;
    private String abn;
    private String phone;
    private String status;
    private String state;
    private StatusReference statusReference;
}
