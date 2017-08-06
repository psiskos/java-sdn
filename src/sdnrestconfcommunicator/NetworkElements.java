/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.ArrayList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author pas
 */
public class NetworkElements 
{
    protected String topologyId,username,password,controllerIp,switchUsedForFlows;
    protected String[] nodes,links,flowValues;
    HttpJsonRequest mJSGet,flowJSGet,installFlowJSPut;
    JSONObject jsonTopology,jsonFlows;
    //each url used gets different reply and need different parse reply instance
    ParseJsonReply mParseReply,mFlowParseReply;
    
    NetworkElements(String username,String password,String controllerIp)
    {
        this.username = username;
        this.password = password;
        this.controllerIp = controllerIp;
        
        mJSGet = new HttpJsonRequest();
        jsonTopology = mJSGet.getRestconfInJson(username, password, controllerIp,ControllerUrls.TOPOLOGY_URL);
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
        String flowsUrl = ControllerUrls.NODES_URL + switchName + "/table/" + table;
        flowJSGet = new HttpJsonRequest();
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
        String dropflowsUrl = ControllerUrls.CONFIG_NODES_URL + switchName + "/table/" + table + "/flow/" + flowId;
        HttpJsonRequest dropFlow = new HttpJsonRequest();
        dropFlow.deleteRestconfInJson(username, password, controllerIp,dropflowsUrl);
    }
    
    protected void installFlows(String switchName,String table,String flowId, String flowString)
    {      
        installFlowJSPut = new HttpJsonRequest();
        String installFlowUrl = ControllerUrls.CONFIG_NODES_URL + switchName + "/table/" + table + "/flow/" + flowId;
        installFlowJSPut.putRestconfInJson(username, password, controllerIp,installFlowUrl,flowString);
    }

    
}
