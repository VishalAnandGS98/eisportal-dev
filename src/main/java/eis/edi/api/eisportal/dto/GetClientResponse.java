package eis.edi.api.eisportal.dto;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class GetClientResponse {

    private String responseCode;
    private String responseType;
    private String responseMessage;

    @JsonInclude(Include.NON_NULL)
    List<Map<String,Object>> clientDtls;
}
