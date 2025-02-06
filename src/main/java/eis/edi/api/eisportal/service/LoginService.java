package eis.edi.api.eisportal.service;

import eis.edi.api.eisportal.dto.LoginRequest;
import eis.edi.api.eisportal.dto.LoginResponse;

public interface LoginService {

    LoginResponse loginResp(LoginRequest reqDtls);
}
