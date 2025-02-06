package eis.edi.api.eisportal.service;

import eis.edi.api.eisportal.dto.GetOutboundRequest;
import eis.edi.api.eisportal.dto.GetOutboundResponse;

public interface GetOutboundService {

    GetOutboundResponse outResp(GetOutboundRequest reqDtls);
}
