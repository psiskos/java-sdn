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
    protected String elemUsedForPorts,elemUsedForFlows;
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
    }
    
    protected String[] getTopoNodes()
    {
        return mPR.getNodes();  
    }
    
    protected String[] getTopoLinks()
    {
        return mPR.getLinks();  
    }
    
    //":8181/restconf/operational/opendaylight-inventory:nodes/"
    protected String[] getNodesNoHosts()
    {
        String url = PublicStatics.NODES_URL;//creates url
        nodesJSGet = new HttpJsonRequest();//initialize instance
        //gets response in json object
        jsonNodes = nodesJSGet.getRestconfInJson(username, password, controllerIp,url);
        //creates instance of parse class to parse json object
        mNodesPR = new ParseJsonReply(jsonNodes);
        
        //remove last element which is controller config
        String[] nodes = mNodesPR.getNodesNoHosts();
        return Arrays.copyOf(nodes,nodes.length - 1);
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
    
    //gets link names of selected switch
    protected String[] getNodeConnectors(String element)
    {
        elemUsedForPorts = element;
        
        String url = PublicStatics.NODE_CONNECTOR_URL + element;//creates url
        nodeConJSGet = new HttpJsonRequest();//initialize instance
        //gets response in json object
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        //creates instance of parse class to parse json object
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConnectorIDs();
    }
    
    protected String[] getNodeUtil(String element)
    {
        ArrayList<String> linksTraffic = new ArrayList<>();
        String[] nodeConnectors = getNodeConnectors(element);
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
                    //" Bps  Tx: " + nodeConTraffic[0] +
                    " Bytes/s in " + formatSeconds(seconds));
            linksTraffic.add(nodeConnectors[i] +" Rx: " + avgLinkRcTraffic +
                    //" Bps  Rx: " + nodeConTraffic[1] +
                    " Bytes/s in " + formatSeconds(seconds));
        }
        return linksTraffic.toArray(new String[0]);
    }
   
    
    protected String getNodeConSeconds(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConSeconds();
    }
    
    protected String[] getNodeConBytes(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getNodeConBytes();
    }
    
    protected int getInterfaceSpeed(String nodeConnector)
    {
        String url = PublicStatics.NODE_CONNECTOR_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsPR = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsPR.getInterfaceSpeed();            
    }
    
    protected String getTopoID()
    {
        return mPR.getTopologyID();
    }
    
    protected String[] getFlows(String switchName,String table)
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
    
}
