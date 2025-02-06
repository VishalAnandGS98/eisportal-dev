package eis.edi.api.eisportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.edi.api.eisportal.dto.GetOutboundRequest;
import eis.edi.api.eisportal.dto.GetOutboundResponse;
import eis.edi.api.eisportal.service.GetOutboundService;

@RestController
@RequestMapping("api")
public class GetOutboundController {

     @Autowired
    private GetOutboundService outBoundServ;

    @CrossOrigin(origins = "*") 
    @PostMapping(value ="outBoundMsg",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public GetOutboundResponse inResp(@RequestBody GetOutboundRequest reqDtls)
    {
       return outBoundServ.outResp(reqDtls);
    }
}
