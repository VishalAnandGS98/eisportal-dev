package eis.edi.api.eisportal.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class LoginResponse {

    private String responseCode;
    private String responseType;
    private String responseMessage;

    @JsonInclude(Include.NON_NULL)
    private Map<String,Object> loginDtls;
}
