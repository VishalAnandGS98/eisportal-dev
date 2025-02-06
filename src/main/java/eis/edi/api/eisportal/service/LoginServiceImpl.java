package eis.edi.api.eisportal.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.LoginRequest;
import eis.edi.api.eisportal.dto.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService{

    private static final String FAILURERESPCODE = "1";
    private static final String FAILURERESPTYPE = "Failure";
    private static final String SUCCESSRESPCODE = "0";
    private static final String SUCCESSRESPTYPE = "Success";

    @Override
    public LoginResponse loginResp(LoginRequest reqDtls)
    {
        LoginResponse respDtls = new LoginResponse();
        String userName = null;
        String password = null;

        try{

            userName = reqDtls.getUserName();
            password = reqDtls.getPassword();

            if(userName == null || userName.isEmpty())
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("User Name is Required");
            }
            else if(password == null || password.isEmpty())
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("Password is Requied");
            }
            else 
            {
                Map<String,Object> loginDtls = new LinkedHashMap<String,Object>();
                loginDtls.put("user", userName);
                loginDtls.put("client", "cambria");

                respDtls.setResponseCode(SUCCESSRESPCODE);
                respDtls.setResponseType(SUCCESSRESPTYPE);
                respDtls.setResponseMessage("Login Successfully");
                respDtls.setLoginDtls(loginDtls);
            }

        }
        catch(Exception e)
        {
            respDtls.setResponseCode(FAILURERESPCODE);
            respDtls.setResponseType(FAILURERESPTYPE);
            respDtls.setResponseMessage(e.getMessage());
        }

        return respDtls;
    }
}
