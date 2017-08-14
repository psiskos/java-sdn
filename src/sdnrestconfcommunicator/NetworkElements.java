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
    HttpJsonRequest topoJSGet,flowJSGet,installFlowJSPut,nodeConJSGet;
    JSONObject jsonTopology,jsonFlows,jsonNodeConnectors;
    //each url used gets different reply and need different parse reply instance
    ParseJsonReply mParseReply,mFlowParseReply,mNodeConnectorsParseReply;
    
    NetworkElements(String username,String password,String controllerIp)
    {
        this.username = username;
        this.password = password;
        this.controllerIp = controllerIp;
        
        topoJSGet = new HttpJsonRequest();
        jsonTopology = topoJSGet.getRestconfInJson(username, password, controllerIp,ControllerUrls.TOPOLOGY_URL);
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
    
    protected String[] getNodeConnectors(String element)
    {
        String nodeConUrl = ControllerUrls.NODES_URL + element;//creates url
        nodeConJSGet = new HttpJsonRequest();//gets request
        //makes json object of request
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,nodeConUrl);
        //creates instance of parse calls to parse json object
        mNodeConnectorsParseReply = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsParseReply.getNodeConnectorIDs();
    }
    
    protected String getTopoID()
    {
        return mParseReply.getTopologyID();
    }
    
    protected String[] getFlows(String switchName,String table)
    {
        String flowsUrl = ControllerUrls.NODES_URL + switchName + "/table/" + table;
        flowJSGet = new HttpJsonRequest();
        jsonFlows = topoJSGet.getRestconfInJson(username, password, controllerIp,flowsUrl);
        System.out.println('\n');
        System.out.println(jsonFlows);
        System.out.println('\n');
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
    
}
