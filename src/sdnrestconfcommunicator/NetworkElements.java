/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.ArrayList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import static sdnrestconfcommunicator.SdnRestconfCommunicator.topoRestconfURL;

/**
 *
 * @author pas
 */
public class NetworkElements 
{
    protected String topologyId,username,password,controllerIp,switchUsedForFlows;
    protected String[] nodes,links,flowValues;
    HttpGetJson mJSGet,flowJSGet;
    HttpPutJson installFlowJSPut;
    JSONObject jsonTopology,jsonFlows;
    //each url used gets different reply and need different parse reply instance
    ParseJsonReply mParseReply,mFlowParseReply;
    
    NetworkElements(String username,String password,String controllerIp)
    {
        this.username = username;
        this.password = password;
        this.controllerIp = controllerIp;
        
        mJSGet = new HttpGetJson();
        jsonTopology = mJSGet.getRestconfInJson(username, password, controllerIp,topoRestconfURL);
        mParseReply = new ParseJsonReply(jsonTopology);
    }
    
    protected String[] getTopoNodes()
    {
        return mParseReply.getNodes();  
    }
    
    protected String[] getTopoLinks()
    {
        return mParseReply.getLinks();  
    }
    
    protected String getTopoID()
    {
        return mParseReply.getTopologyID();
    }
    
    protected String[] getFlows(String switchName,String table)
    {
        String flowsUrl = SdnRestconfCommunicator.operNodesRestconfURL + switchName + "/table/" + table;
        flowJSGet = new HttpGetJson();
        jsonFlows = mJSGet.getRestconfInJson(username, password, controllerIp,flowsUrl);
        mFlowParseReply = new ParseJsonReply(jsonFlows);
        //we need switch name to perform drop//add flow so it is saved in variable
        switchUsedForFlows = switchName;
        return mFlowParseReply.getFlowsIDs();
    }
    
    protected String[] getFlowsValues(String flowId)
    {
        return mFlowParseReply.getFlowsValues(flowId);
    }
    
    protected void dropFlows(String switchName,String table,String flowId)
    {
        String dropflowsUrl = SdnRestconfCommunicator.configNodesRestconfURL + switchName + "/table/" + table + "/flow/" + flowId;
        HttpDelete dropFlow = new HttpDelete();
        dropFlow.deleteRestconfInJson(username, password, controllerIp,dropflowsUrl);
    }
    
    protected void installFlows()
    {      
        installFlowJSPut = new HttpPutJson();
        String installFlowUrl = ":8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/table/0/flow/2";
        installFlowJSPut.putRestconfInJson(username, password, controllerIp,installFlowUrl);
    }

    
}
