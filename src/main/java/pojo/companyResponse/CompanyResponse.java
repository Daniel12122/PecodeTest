package pojo.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private boolean hasNextPage;
    private int totalElements;
    private ArrayList<Result> results;
    private String currentPage;
    private String pageSize;
}
