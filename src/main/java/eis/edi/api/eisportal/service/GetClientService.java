package eis.edi.api.eisportal.service;

import eis.edi.api.eisportal.dto.GetClientRequest;
import eis.edi.api.eisportal.dto.GetClientResponse;

public interface GetClientService {

    GetClientResponse loginResp(GetClientRequest reqDtls);
}
