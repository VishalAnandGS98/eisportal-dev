package eis.edi.api.eisportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import eis.edi.api.eisportal.dto.GetInboundRequest;
import eis.edi.api.eisportal.dto.GetInboundResponse;

@Service
public class GetInboundServiceImpl implements GetInboundService{

     String inBoundQuery = """
select  
ps.name 'Sender', 
pr.name 'Receiver', 
dd.reference,		
dd.type,
ddt.description,
dd.timestamp,  
de2.message 'Map used', 
de3.message 'Ack Trans Status'
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
on (de.envid = de2.envid and de2.event = 7)
left join eistest.eis_tb_dtsevents de3
on (de.envid = de3.envid and de3.event = 20)

where 
 
 dd.type in ( '850', '875', '820', '844', '945', '856', '852', '867') and pr.tpid = ? and de.event = 1
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FAILURERESPCODE = "1";
    private static final String FAILURERESPTYPE = "Failure";
    private static final String SUCCESSRESPCODE = "0";
    private static final String SUCCESSRESPTYPE = "Success";

    @Override
    public GetInboundResponse inResp(GetInboundRequest reqDtls)
    {
        GetInboundResponse respDtls = new GetInboundResponse();
        int clientTpid = 0;

        try
        {
            clientTpid = reqDtls.getClientTpid();

            if(clientTpid == 0)
            {
                respDtls.setResponseCode(FAILURERESPCODE);
                respDtls.setResponseType(FAILURERESPTYPE);
                respDtls.setResponseMessage("TPID is Required");
            }
            else 
            {
                List<Map<String,Object>> inBoundList = jdbcTemplate.queryForList(inBoundQuery,new Object[]{clientTpid});

                if(inBoundList != null && !inBoundList.isEmpty())
                {
                    respDtls.setResponseCode(SUCCESSRESPCODE);
                    respDtls.setResponseType(SUCCESSRESPTYPE);
                    respDtls.setResponseMessage("Details Obtained Successfully");
                    respDtls.setInboundDtls(inBoundList);
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
