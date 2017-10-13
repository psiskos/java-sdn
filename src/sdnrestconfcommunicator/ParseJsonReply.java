/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    
    //-----------------------------FLOWS FUNCTIONS--------------------------------------------------
    
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
    
    //------------------------------------TOPOLOGY FUNCTIONS--------------------------------------------
    protected String getTopoID()
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
    
    protected String[] getTopoNodes()//nodes + hosts
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
    
    protected String[] getTopoLinks()
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
    
    protected String[] getTopoNodesNoHosts()
    {
        ArrayList<String> nodeIds = new ArrayList<>();
        try
        {
            JSONObject nodes = reply.getJSONObject("nodes");
            JSONArray nodeArr = nodes.getJSONArray("node");

            for (int i = 0; i < nodeArr.length(); i++) 
            {
                JSONObject jsonobject = nodeArr.getJSONObject(i);
                if(!jsonobject.getString("id").equals("controller-config"))
                    nodeIds.add(jsonobject.getString("id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodeIds.toArray(new String[0]);
    }
    
    //----------------------------------NODE CONNECTOR FUNCTIONS-----------------------------
    protected String[] getNodeConIDs()
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
    
    protected String getNodeConSeconds()
    {
        
        String nodesConSeconds = null;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            JSONObject obj1 = obj.getJSONObject("opendaylight-port-statistics:flow-capable-node-connector-statistics");           
            JSONObject obj2 = obj1.getJSONObject("duration");

            nodesConSeconds = obj2.getString("second");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodesConSeconds;
    }
    
    protected int getNodeConInterSpeed()
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
    
    protected String getNodeConMac()
    {
        String mac = null;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            mac = obj.getString("flow-node-inventory:hardware-address");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mac;
    }
    
    protected String getNodeConInterName()
    {
        String interfaceName = null;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            interfaceName = obj.getString("flow-node-inventory:name");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return interfaceName;
    }
    
    protected String getNodeConConfig()
    {
        String config = null;
        try
        {
            JSONArray nodeCon = reply.getJSONArray("node-connector");
            JSONObject obj = nodeCon.getJSONObject(0);
            config = obj.getString("flow-node-inventory:configuration");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return config;
    }
    
    
    
    //--------------------ELEMENT VALUES FUNCTIONS-------------------------------------
    
    protected String[] getNodeValues(String nodeID)
    {
        String[] nodeValues = new String[4];//ip,mac
        try
        {
            JSONObject nodes = reply.getJSONObject("nodes");
            JSONArray nodeArr = nodes.getJSONArray("node");
            
            for (int i = 0; i < nodeArr.length(); i++) 
            {
                JSONObject jsonobject = nodeArr.getJSONObject(i);
                
                if (nodeID.equals(jsonobject.getString("id")))
                {
                    nodeValues[0] = jsonobject.getString("flow-node-inventory:hardware");
                    nodeValues[1] = jsonobject.getString("flow-node-inventory:software");
                    nodeValues[2] = jsonobject.getString("flow-node-inventory:serial-number");
                    nodeValues[3] = jsonobject.getString("flow-node-inventory:manufacturer");
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodeValues;
    }
    
    protected String[] getHostValues(String hostId)
    {
        String[] hostValues = new String[2];//ip,mac
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            JSONArray node = obj.getJSONArray("node");
            for (int i = 0; i < node.length(); i++) 
            {
                JSONObject jsonobject = node.getJSONObject(i);
                if (hostId.equals(jsonobject.getString("node-id")))
                {
                    JSONArray addresses = jsonobject.getJSONArray("host-tracker-service:addresses");
                    JSONObject obj2 = addresses.getJSONObject(0);
                    hostValues[0] = obj2.getString("mac");
                    hostValues[1] = obj2.getString("ip");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return hostValues;
    }
    
    
    
    
}
