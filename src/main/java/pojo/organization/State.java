package pojo.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {
    private int id;
    private String country;
    private String state;
    private String abbreviation;
    private boolean enabled;
}
