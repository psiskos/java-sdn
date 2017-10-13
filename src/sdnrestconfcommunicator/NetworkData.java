/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.jettison.json.JSONObject;
import static sdnrestconfcommunicator.PublicStatics.formatSeconds;

/**
 *
 * @author pas
 */
public class NetworkData 
{
    protected String topologyId,username,password,controllerIp;
    protected String node,elemUsedForFlows;
    protected String[] nodes,links,flowValues;
    protected HttpJsonRequest topoJSGet,flowJSGet,installFlowJSPut,nodeConJSGet,nodesJSGet;
    protected JSONObject jsonTopology,jsonFlows,jsonNodeConnectors,jsonNodes;
    //each url used gets different reply and need different parse reply instance
    ParseJsonReply mPR,mFlowsPR,mNodeConnectorsPR,mNodesPR;//parse reply
    
    NetworkData(String username,String password,String controllerIp)
    {
        this.username = username;
        this.password = password;
        this.controllerIp = controllerIp;
        
        topoJSGet = new HttpJsonRequest();
        jsonTopology = topoJSGet.getRestconfInJson(username, password, controllerIp,PublicStatics.TOPOLOGY_URL);
        mPR = new ParseJsonReply(jsonTopology);
        
        nodesJSGet = new HttpJsonRequest();
        jsonNodes = nodesJSGet.getRestconfInJson(username, password, controllerIp,PublicStatics.NODES_URL);
        mNodesPR = new ParseJsonReply(jsonNodes);
    }
    
    //--------------------------------ELEMENT VALUES----------------------------------------------------------
    protected String[] getNodeValues(String node)//hardware,software,serial,manufacturer
    {
        return mNodesPR.getNodeValues(node);
    }
    
    protected String[] getHostValues(String hostId)//ip and mac
    {
        return mPR.getHostValues(hostId);
    }
    
    protected String[] getnNodeConValues(String nodeCon,String node)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + node + "/node-connector/" + nodeCon;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        
        //id,interface speed,uptime,mac,interface name,configuration,
        //transmitted,received,avg bps Tx,avg bps Rx
        String[] nodeConValues = new String[9];
        
        nodeConValues[0] = String.valueOf(mNodeConnectorsPR.getNodeConInterSpeed());
        nodeConValues[1] = mNodeConnectorsPR.getNodeConSeconds();
        nodeConValues[2] = mNodeConnectorsPR.getNodeConMac();
        nodeConValues[3] = mNodeConnectorsPR.getNodeConInterName();
        nodeConValues[4] = mNodeConnectorsPR.getNodeConConfig();
        
        String[] bytes = mNodeConnectorsPR.getNodeConBytes();
        nodeConValues[5] = bytes[0];//transmitted
        nodeConValues[6] = bytes[1];//received
        
        double avgLinkTrTraffic, avgLinkRcTraffic;
        
        //avg transmitted traffic in bytes/s of node connector
        nodeConValues[7] = new DecimalFormat("#.##").format(Double.parseDouble(nodeConValues[5])
                /Integer.parseInt(nodeConValues[1]));
        
        //avg received traffic in bytes/s of node connector
        nodeConValues[8] = new DecimalFormat("#.##").format(Double.parseDouble(nodeConValues[6])
                /Integer.parseInt(nodeConValues[1]));
        
        return nodeConValues;
    }
    
    
    //-----------------------------------NODE CONNECTOR FUNCTIONS-----------------------------------------
    //gets link names of selected switch
    protected String[] getNodeConIDs(String node)
    {
        this.node = node;
        
        String url = PublicStatics.NODE_CONNECTOR_URL + node;//creates url
        nodeConJSGet = new HttpJsonRequest();//initialize instance
        //gets response in json object
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        //creates instance of parse class to parse json object
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConIDs();
    }
   
    
    protected String getNodeConSeconds(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + node + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConSeconds();
    }
    
    protected String[] getNodeConBytes(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + node + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConBytes();
    }
    
    protected int getNodeConInterSpeed(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + node + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConInterSpeed();            
    }
    
    //-------------------------------TOPOLOGY FUNCTIONS----------------------------------------
    protected String getTopoID()
    {
        return mPR.getTopoID();
    }
    
    protected String[] getTopoNodes()
    {
        return mPR.getTopoNodes();  
    }
    
    protected String[] getTopoLinks()
    {
        return mPR.getTopoLinks();  
    }
    
    //":8181/restconf/operational/opendaylight-inventory:nodes/"
    protected String[] getNodesNoHosts()
    { 
        return mNodesPR.getTopoNodesNoHosts();
    }
    
    protected String[] getHosts()
    {
        String[] nodesNoHosts = getNodesNoHosts();
        String[] allNodes = getTopoNodes();
        ArrayList<String> hosts = new ArrayList<>();
        
        for (int i=0; i < allNodes.length;i++)
        {
            if (!Arrays.asList(nodesNoHosts).contains(allNodes[i]))
                hosts.add(allNodes[i]);
        }
        
        return hosts.toArray(new String[0]);
    }
    
    //-----------------------------FLOWS FUNCTIONS---------------------------------------------------------
    protected String[] getFlowIDs(String switchName,String table)
    {
        //we need switch name to perform drop//add flow so it is saved in variable
        elemUsedForFlows = switchName;
        
        String flowsUrl = PublicStatics.NODE_CONNECTOR_URL + switchName + "/table/" + table;
        flowJSGet = new HttpJsonRequest();
        jsonFlows = flowJSGet.getRestconfInJson(username, password, controllerIp,flowsUrl);
        mFlowsPR = new ParseJsonReply(jsonFlows);
        
        return mFlowsPR.getFlowsIDs();
    }
    
    protected String[] getFlowsValues(String flowId)
    {
        return mFlowsPR.getFlowsValues(flowId);
    }
    
    protected void dropFlows(String switchName,String table,String flowId)
    {
        String dropflowsUrl = PublicStatics.CONFIG_NODE_CONNECTOR_URL + switchName + "/table/" + table + "/flow/" + flowId;
        HttpJsonRequest dropFlow = new HttpJsonRequest();
        dropFlow.deleteRestconfInJson(username, password, controllerIp,dropflowsUrl);
    }
    
    //---------------------------------------UTILITY FUNCTIONS-----------------------------------
    protected String[] getNodeUtil(String node)
    {
        ArrayList<String> linksTraffic = new ArrayList<>();
        String[] nodeConnectors = getNodeConIDs(node);
        double avgLinkTrTraffic, avgLinkRcTraffic;
        int seconds;
        DecimalFormat df = new DecimalFormat("#.##");

        for (int i=0; i < nodeConnectors.length; i++ )
        {
            String[] nodeConTraffic = getNodeConBytes(nodeConnectors[i]);
            seconds = Integer.parseInt(getNodeConSeconds(nodeConnectors[i]));
            
            avgLinkTrTraffic = Double.parseDouble(nodeConTraffic[0]) / seconds;
            avgLinkRcTraffic = Double.parseDouble(nodeConTraffic[1]) / seconds;
            
            avgLinkTrTraffic = Double.parseDouble(df.format(avgLinkTrTraffic));
            avgLinkRcTraffic = Double.parseDouble(df.format(avgLinkRcTraffic));
            
            linksTraffic.add(nodeConnectors[i] +" Tx: " + avgLinkTrTraffic +
                    " Bytes/s in " + formatSeconds(seconds));
            linksTraffic.add(nodeConnectors[i] +" Rx: " + avgLinkRcTraffic +
                    " Bytes/s in " + formatSeconds(seconds));
        }
        return linksTraffic.toArray(new String[0]);
    }
    
}
