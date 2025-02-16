package eis.edi.api.eisportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.GetClientRequest;
import eis.edi.api.eisportal.dto.GetClientResponse;

@Service
public class GetClientServiceImpl implements GetClientService{

    String clientQuery = """
            select record_id 'recordId',user_unique_id 'userUniqueId',client_tpid 'clientTpid'
            from eistest.eis_tb_users_client
            where user_unique_id = ?
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FAILURERESPCODE = "1";
    private static final String FAILURERESPTYPE = "Failure";
    private static final String SUCCESSRESPCODE = "0";
    private static final String SUCCESSRESPTYPE = "Success";

    @Override
    public GetClientResponse loginResp(GetClientRequest reqDtls)
    {
        GetClientResponse respDtls = new GetClientResponse();
        int userUniqueId = 0;

        try{

            userUniqueId = reqDtls.getUniqueId();

            if(userUniqueId == 0)
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("User Unique Id is Required");
            }
            else 
            {
                List<Map<String,Object>> clientList = jdbcTemplate.queryForList(clientQuery,new Object[]{userUniqueId});


                if(clientList != null && !clientList.isEmpty())
                {
                    respDtls.setResponseCode(SUCCESSRESPCODE);
                    respDtls.setResponseType(SUCCESSRESPTYPE);
                    respDtls.setResponseMessage("Details Obtained Successfully");
                    respDtls.setClientDtls(clientList);
                }
                else
                {
                    respDtls.setResponseCode(FAILURERESPCODE);
                    respDtls.setResponseType(FAILURERESPTYPE);
                    respDtls.setResponseMessage("No Data Available");
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
