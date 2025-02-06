package eis.edi.api.eisportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eis.edi.api.eisportal.dto.LoginRequest;
import eis.edi.api.eisportal.dto.LoginResponse;
import eis.edi.api.eisportal.service.LoginService;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    private LoginService loginServ;

    @CrossOrigin(origins = "*") 
    @PostMapping(value ="login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse loginResp(@RequestBody LoginRequest reqDtls)
    {
       return  loginServ.loginResp(reqDtls);
    }

}
