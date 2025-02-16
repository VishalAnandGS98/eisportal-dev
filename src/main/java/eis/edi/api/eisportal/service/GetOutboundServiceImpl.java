package eis.edi.api.eisportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.GetOutboundRequest;
import eis.edi.api.eisportal.dto.GetOutboundResponse;

@Service
public class GetOutboundServiceImpl implements GetOutboundService{

    
     String outBoundQuery = """
   select 

pr.name 'receiver', ps.name 'sender', 
dd.type, 
ddt.description, 
dd.reference,
dd.ack,
de.message, 
--cast(cast( b.content as varbinary(max)) as varchar(max)), 
det.description, 
det.event_type 'eventType', 
de2.message 'transmission', 
de3.message 'ackStatus'  ,
de.ts 'ediCreatedtTme',
de2.ts 'transmissionTime',
de3.ts 'ackTime'
--, de.event_id, de.mid, de.envid, dd.reference, dd.timestamp 
from eistest.eis_tb_DTSDocuments dd 
  inner join
  eistest.eis_tb_partners pr
  on pr.tpid = dd.tpid_recipient
  inner join
  eistest.eis_tb_partners ps
  on ps.tpid = dd.tpid_sender
  inner join
  eistest.eis_tb_dtsevents de
  on de.envid = dd.envid
  inner join
  eistest.eis_tb_DTSEventTypes det
  on det.event_type = de.event
  inner join
  eistest.eis_tb_batches b
  on b.BatchID = de.mid
  inner join
  eistest.eis_tb_DTSDocTypes ddt
on ddt.doctype = dd.type
left join eistest.eis_tb_dtsevents de2
on (de.envid = de2.envid and de2.event = 6)
left join eistest.eis_tb_dtsevents de3
on (de.envid = de3.envid and de3.event = 20)
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FAILURERESPCODE = "1";
    private static final String FAILURERESPTYPE = "Failure";
    private static final String SUCCESSRESPCODE = "0";
    private static final String SUCCESSRESPTYPE = "Success";

    @Override
    public GetOutboundResponse outResp(GetOutboundRequest reqDtls)
    {
        GetOutboundResponse respDtls = new GetOutboundResponse();
        int clientTpid = 0;

        try
        {
            clientTpid = reqDtls.getClientTpid();


            if(clientTpid == 0)
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("Client Tpid is Required");
            }
            else 
            {
                List<Map<String,Object>> outBoundList = jdbcTemplate.queryForList(outBoundQuery);

                if(outBoundList != null && !outBoundList.isEmpty())
                {
                    respDtls.setResponseCode(SUCCESSRESPCODE);
                    respDtls.setResponseType(SUCCESSRESPTYPE);
                    respDtls.setResponseMessage("Details Obtained Successfully");
                    respDtls.setOutboundDtls(outBoundList);
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
