/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author pas
 */
public class NetworkElements 
{
    protected String topologyId,username,password,controllerIp;
    protected String elemUsedForPorts,elemUsedForFlows;
    protected String[] nodes,links,flowValues;
    protected HttpJsonRequest topoJSGet,flowJSGet,installFlowJSPut,nodeConJSGet;
    protected JSONObject jsonTopology,jsonFlows,jsonNodeConnectors;
    //each url used gets different reply and need different parse reply instance
    ParseJsonReply mParseReply,mFlowParseReply,mNodeConnectorsParseReply;
    
    NetworkElements(String username,String password,String controllerIp)
    {
        this.username = username;
        this.password = password;
        this.controllerIp = controllerIp;
        
        topoJSGet = new HttpJsonRequest();
        jsonTopology = topoJSGet.getRestconfInJson(username, password, controllerIp,PublicStatics.TOPOLOGY_URL);
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
        elemUsedForPorts = element;
        
        String url = PublicStatics.NODES_URL + element;//creates url
        nodeConJSGet = new HttpJsonRequest();//initialize instance
        //gets response in json object
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        //creates instance of parse class to parse json object
        mNodeConnectorsParseReply = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsParseReply.getNodeConnectorIDs();
    }
    
    protected String[] getNodeConBytes(String nodeConnector)
    {
        String url = PublicStatics.NODES_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsParseReply = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsParseReply.getNodeConBytes();
    }
    
    protected String getTopoID()
    {
        return mParseReply.getTopologyID();
    }
    
    protected String[] getFlows(String switchName,String table)
    {
        //we need switch name to perform drop//add flow so it is saved in variable
        elemUsedForFlows = switchName;
        
        String flowsUrl = PublicStatics.NODES_URL + switchName + "/table/" + table;
        flowJSGet = new HttpJsonRequest();
        jsonFlows = flowJSGet.getRestconfInJson(username, password, controllerIp,flowsUrl);
        mFlowParseReply = new ParseJsonReply(jsonFlows);
        
        return mFlowParseReply.getFlowsIDs();
    }
    
    protected String[] getFlowsValues(String flowId)
    {
        return mFlowParseReply.getFlowsValues(flowId);
    }
    
    protected void dropFlows(String switchName,String table,String flowId)
    {
        String dropflowsUrl = PublicStatics.CONFIG_NODES_URL + switchName + "/table/" + table + "/flow/" + flowId;
        HttpJsonRequest dropFlow = new HttpJsonRequest();
        dropFlow.deleteRestconfInJson(username, password, controllerIp,dropflowsUrl);
    }
    
}
