package eis.edi.api.eisportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.edi.api.eisportal.dto.GetClientRequest;
import eis.edi.api.eisportal.dto.GetClientResponse;
import eis.edi.api.eisportal.service.GetClientService;


@RestController
@RequestMapping("api")
public class GetClientController {

    @Autowired
    private GetClientService clientServ;

    @CrossOrigin(origins = "*") 
    @PostMapping(value ="v1/userClient",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public GetClientResponse loginResp(@RequestBody GetClientRequest reqDtls)
    {
       return  clientServ.loginResp(reqDtls);
    }

}
