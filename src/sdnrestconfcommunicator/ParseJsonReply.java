/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author pas
 */
public class ParseJsonReply 
{
    final private JSONObject reply;
    
    ParseJsonReply(JSONObject reply)
    {
        this.reply = reply;        
    }
    
    protected String getFlows()
    {
        String[] flowsTable = null;
        
        try
        {
            JSONArray netFlows = reply.getJSONArray("flow-node-inventory:table");
            JSONObject obj = netFlows.getJSONObject(0);
            JSONArray flow = obj.getJSONArray("flow");
            flowsTable = new String[flow.length()];
            for (int i = 0; i < flow.length(); i++) 
            {
                JSONObject jsonobject = flow.getJSONObject(i);
                flowsTable[i] = jsonobject.getString("id");
                System.out.println(flowsTable[i]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return flowsTable.toString();
        
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
