/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.ArrayList;
import java.util.Iterator;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author pas
 */
public class ParseJsonReply
{
    final private JSONObject reply;
    JSONArray flow;
    ArrayList<String> flowsValues;
    
    ParseJsonReply(JSONObject reply)
    {
        this.reply = reply;        
    }
    
    protected String[] getFlowsIDs()
    {
        String[] flowsIdTable = null;
        
        try
        {
            JSONArray netFlows = reply.getJSONArray("flow-node-inventory:table");
            JSONObject obj = netFlows.getJSONObject(0);
            flow = obj.getJSONArray("flow");
            flowsIdTable = new String[flow.length()];
            for (int i = 0; i < flow.length(); i++) 
            {
                //flow ids and flow values passed in String arrays
                JSONObject jsonobject = flow.getJSONObject(i);
                flowsIdTable[i] = jsonobject.getString("id");
                //System.out.println(flowsIdTable[i]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return flowsIdTable;
        
    }
    
    protected String[] getFlowsValues(String flowId)
    {
        flowsValues = new ArrayList<String>();
        try
        {
            for (int i = 0; i < flow.length(); i++) 
            {
                //flow ids and flow values passed in String arrays
                JSONObject jsonobject = flow.getJSONObject(i);
                if(flowId.equals(jsonobject.getString("id")))
                {
                   for(Iterator it = jsonobject.keys(); it.hasNext(); ) 
                    {
                        String key = (String)it.next();
                        flowsValues.add(key + ":" + jsonobject.getString(key));
                        System.out.println(key + ":" + jsonobject.getString(key));
                    }     
                }                             
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        //arraylist to string array
        return flowsValues.toArray(new String[0]);
    }
    
    protected String getTopologyID()
    {
        String topologyId = "";
        
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            topologyId = obj.getString("topology-id");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        System.out.println(reply);
        return topologyId;
    }
    
    protected String[] getNodes()
    {
        String[] nodes = null;
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            JSONArray node = obj.getJSONArray("node");
            nodes = new String[node.length()];
            for (int i = 0; i < node.length(); i++) 
            {
                JSONObject jsonobject = node.getJSONObject(i);
                nodes[i] = jsonobject.getString("node-id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodes;
    }
    
    protected String[] getNodeConnectorIDs()
    {
        String[] nodesCons = null;
        try
        {
            JSONArray node = reply.getJSONArray("node");
            JSONObject obj = node.getJSONObject(0);

            JSONArray nodeConnector = obj.getJSONArray("node-connector");
            nodesCons = new String[nodeConnector.length()];
            for (int i = 0; i < nodeConnector.length(); i++) 
            {
                JSONObject jsonobject = nodeConnector.getJSONObject(i);
                nodesCons[i] = jsonobject.getString("id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodesCons;
    }
    
    protected String[] getNodeConBytes()
    {
        String[] nodesConBytes = null;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            JSONObject obj1 = obj.getJSONObject("opendaylight-port-statistics:flow-capable-node-connector-statistics");           
            JSONObject obj2 = obj1.getJSONObject("bytes");
            
            nodesConBytes = new String[2];
            nodesConBytes[0] = obj2.getString("transmitted");
            nodesConBytes[1] = obj2.getString("received");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodesConBytes;
    }
    
    protected int getInterfaceSpeed()
    {
        int interSpeed = 0;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            interSpeed = obj.getInt("flow-node-inventory:current-speed");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return interSpeed;
    }
    
    protected String[] getLinks()
    {
        String[] links = null;
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            JSONArray link = obj.getJSONArray("link");
            links = new String[link.length()];
            for (int i = 0; i < link.length(); i++) 
            {
                JSONObject jsonobject = link.getJSONObject(i);
                links[i] = jsonobject.getString("link-id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return links;
    }
}
