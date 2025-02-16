package eis.edi.api.eisportal.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class GetOutboundResponse {

    private String responseCode;
    private String responseType;
    private String responseMessage;

    @JsonInclude(Include.NON_NULL)
    private List<Map<String,Object>> outboundDtls;
}
