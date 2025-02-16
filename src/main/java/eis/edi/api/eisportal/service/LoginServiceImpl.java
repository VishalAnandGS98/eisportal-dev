package eis.edi.api.eisportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.LoginRequest;
import eis.edi.api.eisportal.dto.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService{

    String loginQuery = """
            select unique_id 'uniqueId',user_email 'userEmail',user_name 'userName',password
            from eistest.eis_tb_users
            where user_name = ?
            and password = ?
            """;;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                List<Map<String,Object>> loginList = jdbcTemplate.queryForList(loginQuery,new Object[]{userName,password});


                if(loginList != null && !loginList.isEmpty())
                {
                    respDtls.setResponseCode(SUCCESSRESPCODE);
                    respDtls.setResponseType(SUCCESSRESPTYPE);
                    respDtls.setResponseMessage("Login Successfully");
                    respDtls.setLoginDtls(loginList);
                }
                else
                {
                    respDtls.setResponseCode(FAILURERESPCODE);
                    respDtls.setResponseType(FAILURERESPTYPE);
                    respDtls.setResponseMessage("Invalid Login");
                }
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
