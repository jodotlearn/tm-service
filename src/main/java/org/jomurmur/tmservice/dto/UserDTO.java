package org.jomurmur.tmservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String account;
    private String password;
    private boolean enabled;
}
