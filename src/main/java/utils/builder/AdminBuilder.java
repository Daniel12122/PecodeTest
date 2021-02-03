package utils.builder;

import pojo.Admin;

public class AdminBuilder {
    public static Admin getDefaultAdmin() {
        return Admin.builder()
                .email("cerelytests@gmail.com")
                .password("123456")
                .build();
    }
}
