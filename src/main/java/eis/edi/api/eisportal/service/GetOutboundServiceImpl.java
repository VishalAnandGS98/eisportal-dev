package eis.edi.api.eisportal.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.GetOutboundRequest;
import eis.edi.api.eisportal.dto.GetOutboundResponse;

@Service
public class GetOutboundServiceImpl implements GetOutboundService{

    private static final String FAILURERESPCODE = "1";
    private static final String FAILURERESPTYPE = "Failure";
    private static final String SUCCESSRESPCODE = "0";
    private static final String SUCCESSRESPTYPE = "Success";

    @Override
    public GetOutboundResponse outResp(GetOutboundRequest reqDtls)
    {
        GetOutboundResponse respDtls = new GetOutboundResponse();
        String client_id = null;
        String userName = null;

        try
        {
            client_id = reqDtls.getClient();
            userName = reqDtls.getUserName();


            if(client_id == null || client_id.isEmpty())
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("Clinet id is Required");
            }
            else if(userName == null || userName.isEmpty())
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("User Name is Required");
            }
            else 
            {
                Map<String,Object> outDtls = new LinkedHashMap<String,Object>();
                outDtls.put("user", userName);
                outDtls.put("client", client_id);

                respDtls.setResponseCode(SUCCESSRESPCODE);
                respDtls.setResponseType(SUCCESSRESPTYPE);
                respDtls.setResponseMessage("Login Successfully");
                respDtls.setOutboundDtls(outDtls);
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
