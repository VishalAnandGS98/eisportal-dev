package eis.edi.api.eisportal.service;

import eis.edi.api.eisportal.dto.GetInboundRequest;
import eis.edi.api.eisportal.dto.GetInboundResponse;

public interface GetInboundService {

    GetInboundResponse inResp(GetInboundRequest reqDtls);
}
