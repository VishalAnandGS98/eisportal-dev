package eis.edi.api.eisportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.edi.api.eisportal.dto.GetInboundRequest;
import eis.edi.api.eisportal.dto.GetInboundResponse;
import eis.edi.api.eisportal.service.GetInboundService;

@RestController
@RequestMapping("api")
public class GetInboundController {

     @Autowired
    private GetInboundService inBoundServ;

    @CrossOrigin(origins = "*") 
    @PostMapping(value ="inBoundMsg",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public GetInboundResponse inResp(@RequestBody GetInboundRequest reqDtls)
    {
       return  inBoundServ.inResp(reqDtls);
    }
}
