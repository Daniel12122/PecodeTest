package pojo.loginResponce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscribers {
    private int id;
    private String name;
    private String employeeStatus;
    private boolean enabled;
    private boolean owner;
    private int userInfoId;
    private int employeeId;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private ArrayList<String> permissions;
    private ArrayList<String> profiles;
    private boolean webAccess;

}
