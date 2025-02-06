package eis.edi.api.eisportal.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String userName;
    private String password;
}
