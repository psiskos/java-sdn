/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
            
            linksTraffic.add(nodeConnectors[i] +"  " + avgLinkTrTraffic +
                    //" Bps  Tx: " + nodeConTraffic[0] +
                    " Bytes in " + formatSeconds(seconds));
            linksTraffic.add(nodeConnectors[i] +"  " + avgLinkRcTraffic +
                    //" Bps  Rx: " + nodeConTraffic[1] +
                    " Bytes in " + formatSeconds(seconds));
        }
        return linksTraffic.toArray(new String[0]);
    }
    
    private String formatSeconds(int seconds)
    {
        String timeFormatted = null;
        if (seconds > 3600)//hours
        {
            int hours = seconds / 3600;
            int mins = (seconds % 3600) / 60;
            int remSec = (seconds % 3600) % 60;
            timeFormatted = hours + " hours " + mins + " minutes " + remSec + " seconds";
        }
        else if (seconds > 60)//minutes
        {
            int mins = seconds/60;
            int remSec = seconds % 60;
            timeFormatted = mins + " minutes " + remSec + " seconds";
        }
        else
            timeFormatted = seconds + " seconds";
        return timeFormatted;
    }
    
    protected String getNodeConSeconds(String nodeConnector)
    {
        String url = PublicStatics.NODES_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsParseReply = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsParseReply.getNodeConSeconds();
    }
    
    protected String[] getNodeConBytes(String nodeConnector)
    {
        String url = PublicStatics.NODES_URL + elemUsedForPorts + "/node-connector/" + nodeConnector;//creates url
        jsonNodeConnectors = nodeConJSGet.getRestconfInJson(username, password, controllerIp,url);
        mNodeConnectorsParseReply = new ParseJsonReply(jsonNodeConnectors);
        return mNodeConnectorsParseReply.getNodeConBytes();
    }
    
    protected int getInterfaceSpeed()
    {
        //mNodeConnectors will already be initialized in getNodeConBytes
        return mNodeConnectorsParseReply.getInterfaceSpeed();
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
